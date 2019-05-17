
package org.acoes.business.impl;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.acoes.business.PaymentsFacade;
import org.acoes.entity.Payment;

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
    
}
