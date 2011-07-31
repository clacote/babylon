package org.plug.babylon.service;

import javax.ejb.Stateless;

/**
 *
 * @author Sryl <cyril.lacote@gmail.com>
 */
@Stateless
public class TestService {
    
    public String test(final String param) {
        return "Le param vaut \"" + param + "\"";
    }
}
