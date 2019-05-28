package org.acoes.business.impl;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.acoes.business.UsersFacade;
import org.acoes.entity.Administrator;
import org.acoes.entity.RegisteredUser;
import org.acoes.entity.Sponsor;
import org.acoes.model.exceptions.UserAlreadyExistsException;

/**
 *
 * @author Manuel
 */
@Stateless
@LocalBean
public class UsersFacadeImpl implements UsersFacade {
    @PersistenceContext(unitName="ACOES_EJB_PU")
    private EntityManager em;
            
    @Override
    public void createUser(Sponsor user) {
        if(doesUserExist(user))
            throw new UserAlreadyExistsException(user.getEmail());
        em.persist(user);
    }

    @Override
    public boolean doesUserExist(RegisteredUser user) {
        return doesUserExist(user.getEmail());
    }

    @Override
    public boolean doesUserExist(String email) {
        return em.find(RegisteredUser.class, email) != null;
    }

    @Override
    public RegisteredUser findUser(String email) {
        return em.find(RegisteredUser.class, email);
    }

    @Override
    public RegisteredUser match(String email, String password) {
        RegisteredUser result = findUser(email);
        if(result != null && result.getPassword().equals(password))
            return result;
        return null;
    }

    @Override
    public void refreshUser(RegisteredUser user) {
        em.merge(user);
    }

    @Override
    public boolean isAdmin(RegisteredUser user) {
        return em.find(Administrator.class, user.getEmail()) != null;
    }

    @Override
    public boolean isSponsor(RegisteredUser user) {
        return isAdmin(user) == false;
    }

}
