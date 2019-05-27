package org.acoes.business.impl;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.acoes.business.AdminsFacade;
import org.acoes.entity.Notification;

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
    public void acceptRequest(Long id) {
        removeRequest(id);
    }

    @Override
    public void declineRequest(Long id) {
        removeRequest(id);
    }
    
    private void removeRequest(Long id){
        Notification n = (Notification) em.find(Notification.class, id);
        if(n != null)
            em.remove(n);
    }
}
