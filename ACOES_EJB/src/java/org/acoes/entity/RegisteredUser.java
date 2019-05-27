package org.acoes.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 @author Manuel
 */
@Entity
@DiscriminatorColumn(name="TYPE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class RegisteredUser implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    protected String email;
    protected String password; 
    
    public RegisteredUser(){}
    
    public RegisteredUser(String username, String password){
        this.email = username;
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RegisteredUser)) {
            return false;
        }
        RegisteredUser other = (RegisteredUser) object;
        return !((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email)));
    }

    @Override
    public String toString() {
        return "RegisteredUser(" + email + ")";
    }
    
}
