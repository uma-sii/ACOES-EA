package org.acoes.business.impl;

import java.util.Collection;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.acoes.business.SponsorshipsFacade;
import org.acoes.entity.Payment;
import org.acoes.entity.SponsoredChild;
import org.acoes.entity.RegisteredUser;
import org.acoes.entity.Sponsor;

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
        /*TypedQuery<SponsoredChild> q = em.createQuery("SELECT c FROM SponsoredChild c JOIN Sponsor s ON s.email = c.sponsor.email WHERE s.email = :itemId", SponsoredChild.class);
        q.setParameter("itemId", user.getEmail());
        q.setFirstResult(page*9);
        q.setMaxResults(9);
        System.out.println("Query getSponsoredChildren: " + q.getResultList().size());
        return q.getResultList();*/
        Sponsor sponsor = em.find(Sponsor.class, user.getEmail());
        return sponsor.getSponsoredChildren();
    }

    @Override
    public void applyForSponsorship(RegisteredUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
