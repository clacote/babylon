package org.plug.babylon.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.plug.babylon.model.Account;
import org.plug.babylon.rest.AbstractFacade;

/**
 * Owner management
 * @author Sryl <cyril.lacote@gmail.com>
 */
@Stateless
public class AccountService extends AbstractFacade<Account> {
    
    @PersistenceContext
    private EntityManager em;

    public AccountService() {
        super(Account.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
