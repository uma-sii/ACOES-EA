package org.acoes.view.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @author Manuel
 */
public class PasswordValidator implements Validator{
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {	
	String password = (String) value;
        if(password.length() < 5){
            createFacesMessage(context, "Error: your password needs to be at least 5 characters long",
                                        "");
        }
    }
    
    private void createFacesMessage(FacesContext context, String summary, String detail){
        FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary(summary);
            message.setDetail(detail);
            context.addMessage("userForm:Password", message);
            throw new ValidatorException(message);
    }
}
