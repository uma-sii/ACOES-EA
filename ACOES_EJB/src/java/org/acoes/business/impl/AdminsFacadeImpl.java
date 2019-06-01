package org.acoes.business.impl;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.acoes.business.AdminsFacade;
import org.acoes.entity.Notification;
import org.acoes.entity.Sponsor;
import org.acoes.entity.SponsoredChild;
import org.acoes.entity.SubscriptionType;

/**
 * @author Manuel
 */
@Stateless
@LocalBean
public class AdminsFacadeImpl implements AdminsFacade {
    @PersistenceContext(unitName="ACOES_EJB_PU")
    private EntityManager em;
    
    @Override
    public List<Notification> getNotifications() {
        Query query = em.createQuery("SELECT n FROM Notification n");
        List<Notification> notifications = (List<Notification>)query.getResultList();
        return notifications;
    }

    @Override
    public void setRequestStatus(Notification n, boolean approved) {
        Sponsor s = n.getApplicant();
        s = em.merge(s);
        s.setRequestApproved(approved);
        n = em.merge(n);
        em.remove(n);
        
        if(approved){
            Query query = em.createQuery("SELECT a FROM SponsoredChild a WHERE a.sponsor IS NULL");
            List<SponsoredChild> children = (List<SponsoredChild>)query.getResultList();
            SponsoredChild sc;
            if(s.getSubscriptionType().equals(SubscriptionType.MONTHLY)){
                sc = children.get(0);
                sc.setSponsor(s);
                em.merge(sc);
            } else {
                for(int i = 0; i < 4; i++){
                    sc = children.get(i);
                    sc.setSponsor(s);
                    em.merge(sc);
                }
            }
            
        }
        
    }
}
