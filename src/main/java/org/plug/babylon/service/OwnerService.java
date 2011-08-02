package org.plug.babylon.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.plug.babylon.model.Owner;
import org.plug.babylon.rest.AbstractFacade;

/**
 * Owner management
 * @author Sryl <cyril.lacote@gmail.com>
 */
@Stateless
public class OwnerService extends AbstractFacade<Owner> {
    
    @PersistenceContext
    private EntityManager em;

    public OwnerService() {
        super(Owner.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}