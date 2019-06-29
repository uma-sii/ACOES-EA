package org.acoes.view;

import java.util.Random;
import org.acoes.entity.RegisteredUser;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Luis, Manuel
 */

@Named(value = "password_recoveryController")
@RequestScoped
public class password_recoveryController {
    
    private String email;
    
    private final static String GEN_PASSWORD_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_-%$";
    
    @Inject
    private SessionControl ctrl;
    
    @Inject
    private EmailSender emailSender;
    
    public password_recoveryController(){
        ctrl = new SessionControl();
    }

    public void sendPassword(){
        if(ctrl.getUsersServices().doesUserExist(email)){
            String newPassword = rndPassword();
            emailSender.sendEmail(email,
                                  emailSubjectMessage(),
                                  emailBodyMessage(newPassword));
            RegisteredUser user = ctrl.getUsersServices().findUser(email);
            user.setPassword(newPassword);
            ctrl.getUsersServices().refreshUser(user);
            facesMessage(FacesMessage.SEVERITY_INFO, okMessage());
        } else{
            facesMessage(FacesMessage.SEVERITY_ERROR,
                         email.isEmpty() ? emptyEmailMessage() : userNotFoundMessage());
        }
    }
    
    private void facesMessage(Severity type, String message){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(type, message, message));
    }

    private String emailSubjectMessage(){
        if(ctrl.getLanguage().equals("EN"))
            return "[ACOES] Password recovery";
        return "[ACOES] Recuperación de contraseña";
    }
    
    private String emailBodyMessage(String newPassword){
        if(ctrl.getLanguage().equals("EN"))
            return "Your new password is " + newPassword;
        return "Su nueva clave es " + newPassword;
    }
    
    private String okMessage(){
        if(ctrl.getLanguage().equals("EN"))
            return "Recovery email is being sent";
        return "Se está enviando su nueva contraseña";
    }
    
    private String emptyEmailMessage(){
        if(ctrl.getLanguage().equals("EN"))
            return "Empty email inserted";
        return "Es necesario insertar un email";
    }
    
    private String userNotFoundMessage(){
        if(ctrl.getLanguage().equals("EN"))
            return "No user related to that email";
        return "No hay ningun usuario registrado con ese email";
    }
    
    private String rndPassword(){
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        int len = rnd.nextInt(9) + 8;
        int refLen = GEN_PASSWORD_CHARACTERS.length();
        for(int i = 0; i < len; i++)
            sb.append(GEN_PASSWORD_CHARACTERS.charAt(rnd.nextInt(refLen)));
        return sb.toString();
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
