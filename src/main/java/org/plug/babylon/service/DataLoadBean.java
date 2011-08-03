package org.plug.babylon.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.plug.babylon.model.Account;
import org.plug.babylon.model.Ope;
import org.plug.babylon.model.Owner;

/**
 * Bean for loading dummy data in DB at startup
 * @author Sryl <cyril.lacote@gmail.com>
 */
@Singleton
@Startup
public class DataLoadBean {
    
    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private OwnerService ownerService;
    
    @EJB
    private AccountService accountService;

    private static final Date NOW = new Date();
    
    private static final Log LOG = LogFactory.getLog(DataLoadBean.class);
    
    @PostConstruct
    public void load() {
        
        LOG.info("Begin load");
        
        // Currencies
        final Currency euro = Currency.getInstance("EUR");
        final Currency pond = Currency.getInstance("GBP");

        // Owners
        final Owner cyril = new Owner("Cyril Lacôte", "cyril.lacote@gmail.com");
        final Owner agnes = new Owner("Agnès Crépet", "agnes.crepet@gmail.com");
        if (ownerService.count() <= 0) {

            LOG.info("Loading dummy owners");

            em.persist(cyril);
            em.persist(agnes);
        } else {

            LOG.info("Useless to load owners (already there!)");
        }

        // Accounts
        if (accountService.count() <= 0) {

            LOG.info("Loading dummy accounts");
            
            final Account a1 = new Account("ABCD", euro, cyril);
            em.persist(a1);
            final Account a2 = new Account("EFGH", pond, cyril);
            em.persist(a2);
            final Account a3 = new Account("IJKL", euro, agnes);
            em.persist(a3);

            createOperations(a1, 2000);
            createOperations(a2, 5);
            createOperations(a3, 4000);
        } else {

            LOG.info("Useless to load accounts (already there!)");
        }

        LOG.info("End loading");
    }
    
    protected void createOperations(final Account a, final int nbOperations) {
        for (int i = 0; i < nbOperations; i++) {
            em.persist(new Ope(a, randomAmount(), randomDate()));
        }
    }
    
    protected BigDecimal randomAmount() {
        return BigDecimal.valueOf(RandomUtils.nextDouble() * 1000).setScale(2, RoundingMode.HALF_UP);
    }
    
    protected Date randomDate() {
        return DateUtils.addDays(NOW, -Math.abs(RandomUtils.nextInt(1000)));
    }
}
