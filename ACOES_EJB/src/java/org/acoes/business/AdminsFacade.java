package org.acoes.business;

import java.util.List;
import javax.ejb.Local;
import org.acoes.entity.Notification;

/**
 * @author Manuel
 */
@Local
public interface AdminsFacade {
    public List<Notification> getNotifications();
    public void acceptRequest(Long id);
    public void declineRequest(Long id);
}
