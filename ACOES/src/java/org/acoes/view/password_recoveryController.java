package org.acoes.view;

import org.acoes.entity.RegisteredUser;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Luis
 */

@Named(value = "password_recoveryController")
@RequestScoped
public class password_recoveryController {
    
    private String email;
    
    @Inject
    private SessionControl ctrl;
    
    public password_recoveryController(){
        ctrl = new SessionControl();
    }

    public void sendPasswordButton(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String message;
        if(ctrl.getUsersServices().doesUserExist(email)){
               if(ctrl.getLanguage().equals("EN")){
                message = "Recovery email is being sent";
            }else{
                message = "Se está enviando su nueva contraseña";
            }
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
        
        }else if(email.isEmpty()){
            if(ctrl.getLanguage().equals("EN")){
                message = "Empty email inserted";
            }else{
                message = "Es necesario insertar un email";
            }
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
        }else{
            if(ctrl.getLanguage().equals("EN")){
                message = "No user related to that email";
            }else{
                message = "No hay ningun usuario registrado con ese email";
            }
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));    
        }
         
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
