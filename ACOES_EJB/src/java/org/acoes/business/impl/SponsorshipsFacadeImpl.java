package org.acoes.business.impl;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.acoes.business.SponsorshipsFacade;
import org.acoes.entity.Sponsor;
import org.acoes.entity.SponsoredChild;
import org.acoes.entity.RegisteredUser;

/**
 * @author Manuel
 */
@Stateless
@LocalBean
public class SponsorshipsFacadeImpl implements SponsorshipsFacade {
    @PersistenceContext(unitName="ACOES_EJB_PU")
    private EntityManager em;
    
    @Override
    public List<SponsoredChild> getSponsoredChildren(RegisteredUser user) {
        Sponsor sponsor = (Sponsor)em.find(RegisteredUser.class, user.getEmail());
        return sponsor.getSponsoredChildren();
    }

    @Override
    public void applyForSponsorship(RegisteredUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
