
package org.acoes.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Manuel
 */
@Named(value = "collaborateController")
@RequestScoped
public class Collaborate_Controller {
    public final static String SUBJECT = "Collaborate with ACOES";
            
    private String firstName;
    private String lastName;
    private String email;
    private String message;
    
    @Inject
    private EmailSender emailSender;
    
    public String Send(){
        StringBuilder sb = new StringBuilder();
        sb.append("First Name: ").append(firstName).append("\n");
        sb.append("Last Name: ").append(lastName).append("\n");
        sb.append("Email: <").append(email).append(">\n\n");
        sb.append(message);
        emailSender.sendToACOES(SUBJECT, sb.toString());
        return "index.xhtml";
    }
    
    //// Getters and setters
    public String getFirstName(){
        return firstName;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getMessage(){
        return message;
    }
    
    public void setMessage(String message){
        this.message = message;
    }
}
