package org.acoes.view.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import org.acoes.business.UsersFacade;

/**
 *
 * @author Manuel
 */
public class EmailValidator implements Validator{
    
    @Inject
    private UsersFacade usersService;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {	
	String email = (String) value;
        
        if(usersService.doesUserExist(email)){
            createFacesMessage(context, "Error: User already exists", "Error: User already exists");
        }
        
        if(email.length() == 0){
            createFacesMessage(context, "Error: Email is not valid", "Email cannot be empty.");
        }
        
	if(!email.contains("@") || email.startsWith("@") || email.charAt(email.length()-1) == '@') {
            createFacesMessage(context, "Error: Email is not valid", "Error: Email is not valid.");
        }
    }
    
    private void createFacesMessage(FacesContext context, String summary, String detail){
        FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary(summary);
            message.setDetail(detail);
            context.addMessage("userForm:Email", message);
            throw new ValidatorException(message);
    }
}
