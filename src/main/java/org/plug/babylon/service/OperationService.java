package org.plug.babylon.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.plug.babylon.model.Ope;

/**
 * Bank statements management
 * @author Sryl <cyril.lacote@gmail.com>
 */
@Stateless
public class OperationService extends AbstractService<Ope> {
    
    @PersistenceContext
    private EntityManager em;

    public OperationService() {
        super(Ope.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
