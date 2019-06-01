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
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
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
    
    private Locale locale;

    private static Map<String,Object> countries;
    
    static {
       countries = new LinkedHashMap<>();
       countries.put("Spanish", new Locale("es", "ES"));
       countries.put("English", Locale.ENGLISH);
    }
    
    private RegisteredUser user;

    @EJB
    private UsersFacade usersServices;
    
    @EJB
    private SponsorshipsFacade sponsorshipsServices;
    
    @EJB
    private PaymentsFacade paymentsServices;
    
    @EJB
    private AdminsFacade adminsServices;
    
    
    public SessionControl(){}
    
    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }
    
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
        if(!loggedIn())
            return "login.xhtml";
        return null;
    }
    
    public String log(){ 
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        if(user == null){
            String message = null;
            if(getLanguage().equals("en")){
                message = "Error: incorrect user or password";
            } else{
                message = "Error: usuario o contraseña incorrecta";
            }
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
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

    public Map<String, Object> getCountries() {
      return countries;
   }

   public Locale getLocale(){
      return locale;
   }

   public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

   //value change event listener
   public void localeChanged(ValueChangeEvent e) {
      String newLocaleValue = e.getNewValue().toString();
      for (Map.Entry<String, Object> entry : countries.entrySet()) {
         if(entry.getValue().toString().equals(newLocaleValue)) {
            FacesContext.getCurrentInstance()
               .getViewRoot().setLocale((Locale)entry.getValue());         
         }
      }
   }
}
