package org.acoes.business.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.acoes.business.SponsorshipsFacade;
import org.acoes.entity.Notification;
import org.acoes.entity.SponsoredChild;
import org.acoes.entity.RegisteredUser;
import org.acoes.entity.Sponsor;
import org.acoes.entity.SubscriptionType;

/**
 * @author Manuel
 */
@Stateless
@LocalBean
public class SponsorshipsFacadeImpl implements SponsorshipsFacade {
    @PersistenceContext(unitName="ACOES_EJB_PU")
    private EntityManager em;
    
    @Override
    public Collection<SponsoredChild> getSponsoredChildren(RegisteredUser user, int page) {
        Sponsor sponsor = em.find(Sponsor.class, user.getEmail());
        return sponsor.getSponsoredChildren();
    }

    @Override
    public void applyForSponsorship(Sponsor user, SubscriptionType type) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
        
        Notification n = new Notification(user, date, type);
        
        em.persist(n);
        
        user.setSubscriptionType(type);
        em.merge(user);
    }

    @Override
    public List<Sponsor> getSponsors(boolean onlyApproved) {
        Query query = null;
        if(onlyApproved){
            query = em.createQuery("SELECT s FROM Sponsor s WHERE s.requestApproved = true");
        } else{
            query = em.createQuery("SELECT s FROM Sponsor s");
        }
        List<Sponsor> sponsors = (List<Sponsor>)query.getResultList();
        return sponsors;
    }
}
