/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.acoes.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Manuel
 */
@Entity
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Sponsor applicant;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfRequest;
    
    private SubscriptionType subscriptionType;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Sponsor getApplicant(){
        return applicant;
    }
    
    public void setApplicant(Sponsor applicant){
        this.applicant = applicant;
    }
    
    public Date getDateOfRequest(){
        return dateOfRequest;
    }
    
    public void setDateOfRequest(Date dateOfRequest){
        this.dateOfRequest = dateOfRequest;
    }
    
    public SubscriptionType getSubscriptionType(){
        return subscriptionType;
    }
    
    public void setSubscriptionType(SubscriptionType type){
        subscriptionType = type;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "org.acoes.entity.Notification[ id=" + id + " ]";
    }
    
}
