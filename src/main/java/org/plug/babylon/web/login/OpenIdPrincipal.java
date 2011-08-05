package org.plug.babylon.web.login;

import java.security.Principal;
import org.brickred.socialauth.Profile;

/**
 * OpenID JAAS Principal
 * @author Sryl <cyril.lacote@gmail.com>
 */
public class OpenIdPrincipal implements Principal {

    Profile profile;

    public OpenIdPrincipal(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String getName() {
        return profile.getEmail();
    }    
}
