/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.acoes.view;

import org.acoes.entity.RegisteredUser;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.acoes.business.AdminsFacade;
import org.acoes.business.PaymentsFacade;
import org.acoes.business.SponsorshipsFacade;
import org.acoes.business.UsersFacade;
import org.acoes.entity.Administrator;
import org.acoes.entity.Sponsor;

/**
 * @author cris, manuel
 */
@Named(value = "sessionControl")
@SessionScoped
public class SessionControl implements Serializable {
    
    private RegisteredUser user;

    @EJB
    private UsersFacade usersServices;
    
    @EJB
    private SponsorshipsFacade sponsorshipsServices;
    
    @EJB
    private PaymentsFacade paymentsServices;
    
    @EJB
    private AdminsFacade adminsServices;
    
    public SessionControl(){ }
    
    public void setUser(RegisteredUser user){
        this.user = user;
    }
    
    public RegisteredUser getUser(){
        return user;
    }
    
    public Administrator getAdmin(){
        return (Administrator)user;
    }
    
    public Sponsor getSponsor(){
        return (Sponsor)user;
    }
    
    public UsersFacade getUsersServices(){
        return usersServices;
    }
    
    public SponsorshipsFacade getSponsorshipsServices(){
        return sponsorshipsServices;
    }
    
    public PaymentsFacade getPaymentsServices(){
        return paymentsServices;
    }
    
    public AdminsFacade getAdminsServices(){
        return adminsServices;
    }
    
    public void refreshUser(){
        usersServices.refreshUser(user);
    }
    
    public boolean loggedIn(){
        return user != null;
    }
    
    public String redirectIfNeeded(){
        System.out.println("Redirect if needed");
        if(!loggedIn())
            return "login.xhtml";
        return null;
    }
    
    public String log(){ 
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        if(user == null){
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, incorrect user or password", "Error, incorrect user or password"));
            return "login.xhtml";
        }
        
        return "index.xhtml";
    }
    
    public String logout(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().invalidateSession();
        user = null;
        return "index.xhtml";
    }
}
