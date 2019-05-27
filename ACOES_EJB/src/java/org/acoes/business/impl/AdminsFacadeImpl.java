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
    }
}
