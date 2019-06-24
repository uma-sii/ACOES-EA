package org.acoes.business;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
        public class Tuple {
            int x, y;
            public Tuple(int x, int y){ 
                this.x = x;
                this.y = y;
            }
        }
        
        private final List<Payment> payments;
        
        public Stats(List<Payment> payments){
            this.payments = payments;
            Collections.sort(this.payments, new Comparator<Payment>(){
                @Override
                public int compare(Payment t, Payment t1) {
                    return t1.getTimestamp().compareTo(t.getTimestamp());
                }
            });
        }
        
        public int totalAmount(){
            int sum = 0;
            for(Payment p : payments)
                sum += p.getAmount();
            return sum;
        }
        
        public Map<Integer, Integer> totalAmountPerMonth(){
            HashMap<Integer, Integer> results = new HashMap<>();
            int month;
            int sum;
            
            for(int i = 1; i <= 12; i++)
                results.put(i, 0);
            
            for(Payment p : payments){
                month = paymentMonth(p);
                sum = results.get(month);
                results.put(month, sum + p.getAmount());
            }
            return results; 
        }
        
        public Map<Tuple, Integer> totalAmountPerMonthAndYear(){
            HashMap<Tuple, Integer> results = new HashMap<>();
            Tuple monthYear;
            int sum;
            for(Payment p : payments){
                monthYear = new Tuple(paymentMonth(p), paymentYear(p));
                if(!results.containsKey(monthYear)){
                    results.put(monthYear, 0);
                    sum = 0;
                } else{
                    sum = results.get(monthYear);
                }
                results.put(monthYear, sum + p.getAmount());
            }
            return results; 
        }
        
        private int paymentMonth(Payment p){
            Calendar cal = Calendar.getInstance();
            cal.setTime(p.getTimestamp());
            return cal.get(Calendar.MONTH) + 1;
        }
        
        private int paymentYear(Payment p){
            Calendar cal = Calendar.getInstance();
            cal.setTime(p.getTimestamp());
            return cal.get(Calendar.YEAR);
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
