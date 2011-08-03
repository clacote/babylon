package org.plug.babylon.service;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.Before;

/**
 * Base class for JUnit tests using EJB (in embeddable EJB container)
 * @author Sryl <cyril.lacote@gmail.com>
 */
// FIXME Can't use embedded EJBContainer without javaee-api.jar on classpath (NetBeans warning : java-ee-web-api.jar code is deleted at runtime)
public class AbstractTestEJBContext {

    private EJBContainer container;

    protected Object getLocalEJB(String name) throws NamingException {
        return container.getContext().lookup("java:global/classes/" + name);
    }

    @Before
    public void setUp() throws Exception {
        container = EJBContainer.createEJBContainer();
    }

    @After
    public void tearDown() {
        if (container != null) {
            container.close();
        }
    }
    
}
