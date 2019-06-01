package org.acoes.view;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
/**
 * @author Manuel
 */
@Named(value = "donation")
@RequestScoped
public class Donation implements Serializable  {
    private int amount;
    private String message;
    
    @Inject
    private SessionControl sessionControl;
    
    public Donation() {
        amount = 0;
        message = "";
    }
    
    public int getAmount(){
        return amount;
    }
    
    public void setAmount(int amount){
        this.amount = amount;
    }
    
    public String getMessage(){
        return message;
    }
    
    public void setMessage(String msg){
        message = msg;
    }
    
    public void donate(){
        if(sessionControl.getLanguage().equals("en")){
            message = "Thanks for contributing to our cause";
        } else{
            message = "Gracias por contribuir a nuestra causa";
        }
        amount = 0;
    }
}
