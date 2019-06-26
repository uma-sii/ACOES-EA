package org.acoes.business;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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
        public class Tuple<A,B> {
            A x;
            B y;
            public Tuple(A x, B y){ 
                this.x = x;
                this.y = y;
            }
            
            public A x(){ return x; }
            public B y(){ return y; }
        }
        
        private final List<Payment> payments;
        
        private int totalAmount;
        private int totalDonations;
        private int totalSubscriptions;
        
        private int averageIncome;
        private int averageDonation;
        private int averageSubscription;
        
        private List<Tuple<String, Integer>> incomeLastSixMonths;
        
        private int nDonations;
        private int nSubscriptions;
        
        public Stats(List<Payment> payments){
            this.payments = payments;
            Collections.sort(this.payments, new Comparator<Payment>(){
                @Override
                public int compare(Payment t, Payment t1) {
                    return t1.getTimestamp().compareTo(t.getTimestamp());
                }
            });
            
            totalAmount = 0;
            nDonations = 0;
            for(Payment p : payments){
                totalAmount += p.getAmount();
                if(p.getConcept().toUpperCase().equals("DONATION")){
                    nDonations++;
                    totalDonations += p.getAmount();
                }
                else if(p.getConcept().toUpperCase().equals("SUBSCRIPTION")){
                    nSubscriptions++;
                    totalSubscriptions += p.getAmount();
                }
            }
            if(payments.size() > 0 && nDonations > 0 && nSubscriptions > 0){
                averageIncome = totalAmount/payments.size();
                averageDonation = totalDonations/nDonations;
                averageSubscription = totalSubscriptions/nSubscriptions;
            } else {
                averageIncome = 0;
                averageDonation = 0;
                averageSubscription = 0;
            }

            
            incomeLastSixMonths();
        }
        
        public int numPayments(){
            return payments.size();
        }
        
        public int numDonations(){
            return nDonations;
        }
        
        public int numSubscriptions(){
            return nSubscriptions;
        }
        
        public int totalAmount(){
            return totalAmount;
        }
        
        public int totalDonations(){
            return totalDonations;
        }
        
        public int totalSubscriptions(){
            return totalSubscriptions;
        }
        
        public int averageIncome(){
            return averageIncome;
        }
        
        public int averageDonation(){
            return averageDonation;
        }
        
        public int averageSubscription(){
            return averageSubscription;
        }
        
        public Tuple<String, Integer> incomeLastSixMonths(int index){
            return incomeLastSixMonths.get(index);
        }
        
        private void incomeLastSixMonths(){
            TreeMap<String, Integer> results = new TreeMap<>();
            List<Tuple<String, Integer>> ls = new LinkedList<>();
            String monthYear;
            
            ZonedDateTime now = ZonedDateTime.now();
            ZonedDateTime sixMonthsAgo = now.plusMonths(-6);
            
            ZonedDateTime temp;
            for(int i = 0; i <= 6; i++){
                temp = now.plusMonths(-i);
                if(temp.getMonthValue() < 10){
                    monthYear = "0" + temp.getMonthValue() + "/" + temp.getYear();
                } else{
                    monthYear = temp.getMonthValue() + "/" + temp.getYear();
                }
                results.put(monthYear, 0);
            }
            
            for(Payment p : payments){
                if (p.getTimestamp().toInstant().isBefore(sixMonthsAgo.toInstant()))
                    continue;
                monthYear = paymentMonthYear(p);
                results.put(monthYear, results.get(monthYear) + p.getAmount());
            }
            
            for(String label : results.keySet()){
                ls.add(new Tuple<>(label, results.get(label)));
            }
            
            incomeLastSixMonths = ls;
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
        
        private String paymentMonthYear(Payment p){
            int month = paymentMonth(p);
            int year = paymentYear(p);
            if(month < 10)
                return "0" + month + "/" + year;
            return month + "/" + year;
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
