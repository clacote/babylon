package org.plug.babylon.web.login;

import java.io.Serializable;
import java.security.Principal;
import org.brickred.socialauth.Profile;

/**
 * OpenID JAAS Principal
 * @author Sryl <cyril.lacote@gmail.com>
 */
public class OpenIdPrincipal implements Principal, Serializable {

    Profile profile;

    public OpenIdPrincipal(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String getName() {
        if (profile == null) return null;
        return profile.getEmail();
    }

    public Profile getProfile() {
        return profile;
    }
}
