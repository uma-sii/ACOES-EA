package org.acoes.business;

import java.util.List;
import javax.ejb.Local;
import org.acoes.entity.SponsoredChild;
import org.acoes.entity.RegisteredUser;
import org.acoes.entity.Sponsor;

/**
 * Business tier operations related to sponsorships.
 * @author Manuel
 */
@Local
public interface SponsorshipsFacade {
    public List<Sponsor> getSponsors(boolean onlyApproved);
    public List<SponsoredChild> getSponsoredChildren(RegisteredUser user);
    public void applyForSponsorship(RegisteredUser user);
}
