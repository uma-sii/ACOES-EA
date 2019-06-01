package org.acoes.view;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.acoes.business.PaymentsFacade;
import org.acoes.entity.Sponsor;
/**
 * @author Manuel
 */
@Named(value = "donation")
@RequestScoped
public class Donation implements Serializable  {
    
    @Inject
    private PaymentsFacade paymentsServices;
    
    @Inject
    private SessionControl sessionControl;
        
    private int amount;
    private String message;
    

    
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
        
        if(sessionControl.loggedIn()){
            Sponsor applicant = (Sponsor)sessionControl.getUser();
            paymentsServices.userDonation(applicant, this.getAmount());
        } else {
            paymentsServices.anonDonation(this.getAmount());
        }
        
        if(sessionControl.getLanguage().equals("en")){
            message = "Thanks for contributing to our cause";
        } else{
            message = "Gracias por contribuir a nuestra causa";
        }
        
        amount = 0;
    }
}
