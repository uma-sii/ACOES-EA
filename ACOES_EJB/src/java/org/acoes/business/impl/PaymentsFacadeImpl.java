
package org.acoes.business.impl;

import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.acoes.business.PaymentsFacade;
import org.acoes.entity.Payment;
import org.acoes.entity.Sponsor;

/**
 *
 * @author Manuel
 */
@Stateless
@LocalBean
public class PaymentsFacadeImpl implements PaymentsFacade {
    @PersistenceContext(unitName="ACOES_EJB_PU")
    private EntityManager em;
    
    @Override
    public List<Payment> getAllPayments() {
        Query query = em.createQuery("SELECT p FROM Payment p");
        List<Payment> payments = (List<Payment>)query.getResultList();
        return payments;
    }

    @Override
    public Stats getStats() {
        return new Stats(getAllPayments());
    }

    @Override
    public List<Payment> getUserPayments(Sponsor sponsor) {
        Query query = em.createQuery("SELECT p FROM Payment p WHERE p.benefactor.email = '" + sponsor.getEmail() + "'");
        List<Payment> payments = (List<Payment>)query.getResultList();
        return payments;
    }

    @Override
    public void userDonation(Sponsor sponsor, int amount) {
        Date date = new Date();
        
        Payment p = new Payment(sponsor,amount,"Donation");
        p.setTimestamp(date);
        p.setPaymentMethod("Bank transference");
        
        em.persist(p);
    }

    @Override
    public void anonDonation(int amount) {
        Date date = new Date();
        
        Payment p = new Payment();
        p.setConcept("Donation");
        p.setTimestamp(date);
        p.setPaymentMethod("Bank transference");      
        p.setAmount(amount);
        
        em.persist(p);
    }

    @Override
    public void sponsorshipSubscription(Sponsor sponsor, int amount) {
        Date date = new Date();
        
        Payment p = new Payment(sponsor, amount, "Subscription");
        p.setTimestamp(date);
        p.setPaymentMethod("Bank transference");
        
        em.persist(p);        
    }
    
    
}
