package org.acoes.business;

import javax.ejb.Local;
import org.acoes.entity.RegisteredUser;

/**
  Business tier operations related to users.
  @author Manuel
 */
@Local
public interface UsersFacade {
    public void             createUser(RegisteredUser user);
    public boolean          doesUserExist(RegisteredUser user);
    public boolean          doesUserExist(String email);
    public RegisteredUser   findUser(String email);
    public boolean          isAdmin(RegisteredUser user);
    public boolean          isSponsor(RegisteredUser user);
    /**
     * Finds an user with the given email and password.
     * @param email
     * @param password
     * @return if it exists, returns that user. Otherwise, returns null.
     */
    public RegisteredUser   match(String email, String password);
    public void             refreshUser(RegisteredUser user);
}
