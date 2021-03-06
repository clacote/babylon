package org.plug.babylon.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.plug.babylon.model.Account;

/**
 * Owner management
 * @author Sryl <cyril.lacote@gmail.com>
 */
@Stateless
public class AccountService extends AbstractService<Account> {
    
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
