package org.acoes.business;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Local;
import org.acoes.entity.Payment;
import org.acoes.entity.RegisteredUser;
import org.acoes.entity.Sponsor;

/**
 *
 * @author Manuel
 */
@Local
public interface PaymentsFacade {
    public List<Payment> getAllPayments();
    
    public List<Payment> getUserPayments(Sponsor sponsor);
    
    public void userDonation(Sponsor sponsor, int amount);
    public void anonDonation(int amount);

    public void sponsorshipSubscription(Sponsor sponsor, int amount);
    
    public Stats getStats();
    
    public class Stats{
        private final List<Payment> payments;
        
        public Stats(List<Payment> payments){
            this.payments = payments;
        }
        
        public int totalAmount(){
            int sum = 0;
            for(Payment p : payments)
                sum += p.getAmount();
            return sum;
        }
        
        public int numberOfBenefactors(){
            Set<RegisteredUser> benefactors = new HashSet<>();
            for(Payment p : payments){
                benefactors.add(p.getBenefactor());
            }
            return benefactors.size();
        }
        
        public List<Payment> getPayments(){
            return payments;
        }
    }
}
