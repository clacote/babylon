package org.plug.babylon.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.plug.babylon.model.Owner;

/**
 * Owner management
 * @author Sryl <cyril.lacote@gmail.com>
 */
@Stateless
public class OwnerService extends AbstractService<Owner> {

    @PersistenceContext
    private EntityManager em;

    public OwnerService() {
        super(Owner.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Retrieve owner by given email
     * @param email
     * @return Owner found, null if not exists.
     */
    public Owner getByEmail(final String email) {
        return em
            .createNamedQuery(Owner.FIND_BY_EMAIL, Owner.class)
            .setParameter("email", email)
            .getSingleResult();
    }
}
