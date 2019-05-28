/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.acoes.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author Manuel
 */
@Entity
@DiscriminatorValue("SPONSOR")
public class Sponsor extends RegisteredUser {
    private boolean requestApproved;
    
    private String dni;
    
    private String address;
    private String city;
    private String country;
    private int zipcode;
    
    private String phoneNumber;
    
    private Gender gender;
    
    private String firstName;
    private String lastName;
    
    @OneToMany(mappedBy="sponsor", fetch=FetchType.LAZY)
    private List<SponsoredChild> sponsoredChildren = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Payment> payments;
    
    private SubscriptionType subscriptionType;

    public Sponsor(String email, String password) {
        super(email, password);
    }
    
    public boolean getRequestApproved(){
        return requestApproved;
    }
    
    public void setRequestApproved(boolean approved){
        this.requestApproved = approved;
    }
    
    public String getDNI() {
        return dni;
    }
    
    public void setDNI(String id) {
        this.dni = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender g) {
        this.gender = g;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public void setPhoneNumber(String number) {
        this.phoneNumber = number;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
    
    public Collection<SponsoredChild> getSponsoredChildren() {
        return sponsoredChildren;
    }

    public void setSponsoredChildren(List<SponsoredChild> sponsoredChildren) {
        this.sponsoredChildren = sponsoredChildren;
    }
    
    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
    
    public SubscriptionType getSubscriptionType(){
        return subscriptionType;
    }
    
    public void setSubscriptionType(SubscriptionType type){
        subscriptionType = type;
    }
    
    @Override
    public String toString() {
        return "Sponsor(" + email + ")";
    }
    
}
