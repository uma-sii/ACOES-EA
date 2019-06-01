package org.acoes.view;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.acoes.business.PaymentsFacade;
import org.acoes.business.SponsorshipsFacade;
import org.acoes.entity.Sponsor;
import org.acoes.entity.SubscriptionType;
/**
 * @author Manuel
 */
@Named(value = "notifications")
@RequestScoped
public class Notifications implements Serializable  {
    @Inject
    private SponsorshipsFacade sponsorshipsServices;
    
    @Inject
    private PaymentsFacade paymentsServices;
    
    @Inject
    private SessionControl sessionControl;
    
    private String subscriptionType;
    private String message;
    
    public Notifications() {
        subscriptionType = "Monthly";
        message = "";
    }

    public String getMessage(){
        return message;
    }
    
    public void setMessage(String message){
        this.message = message;
    }
    
    public String getSubscriptionType(){
        return subscriptionType;
    }
    
    public void setSubscriptionType(String subscriptionType){
        this.subscriptionType = subscriptionType;
    }
    
    public void applyForSponsorship(){
        System.out.println("Apply for sponsorship");
        int amount = 150;
        SubscriptionType type = SubscriptionType.YEARLY;
        if(subscriptionType.equals("Monthly")){
            type = SubscriptionType.MONTHLY;
            amount = 15;
        }
        Sponsor applicant = (Sponsor)sessionControl.getUser();
        sponsorshipsServices.applyForSponsorship(applicant, type);
        
        paymentsServices.sponsorshipSubscription(applicant, amount);
        
        if(sessionControl.getLanguage().equals("en")){
            message = "Thanks for contributing to our cause";
        } else{
            message = "Gracias por contribuir a nuestra causa";
        }
    }
}
