package org.plug.babylon.service;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateUtils;
import org.plug.babylon.model.Account;
import org.plug.babylon.model.Currency;
import org.plug.babylon.model.Ope;
import org.plug.babylon.model.Owner;

/**
 *
 * @author Sryl <cyril.lacote@gmail.com>
 */
@Singleton
@Startup
public class DataLoadBean {
    
    @PersistenceContext
    EntityManager em;
    
    private static final Date NOW = new Date();
    
    @PostConstruct
    public void load() {
        
        System.out.println("Load database");
        
        // Currencies
        final java.util.Currency cEuro = java.util.Currency.getInstance("EUR"); 
        final Currency euro = new Currency(cEuro.getCurrencyCode(), cEuro.getSymbol());
        em.persist(euro);
        final java.util.Currency cPonds = java.util.Currency.getInstance("GBP");
        final Currency pond = new Currency(cPonds.getCurrencyCode(), cPonds.getSymbol());
        em.persist(pond);

        // Owners
        final Owner cyril = new Owner("Cyril Lacôte", "cyril.lacote@gmail.com");
        em.persist(cyril);
        final Owner agnes = new Owner("Agnès Crépet", "agnes.crepet@gmail.com");
        em.persist(agnes);

        // Accounts
        final Account a1 = new Account("ABCD", euro, cyril);
        em.persist(a1);
        final Account a2 = new Account("EFGH", pond, cyril);
        em.persist(a2);
        final Account a3 = new Account("IJKL", euro, agnes);
        em.persist(a3);
        
        createOperations(a1, 2354);
        createOperations(a2, 3);
        createOperations(a3, 4365);
    }
    
    protected void createOperations(final Account a, final int nbOperations) {
        for (int i = 0; i < nbOperations; i++) {
            em.persist(new Ope(a, randomAmount(), randomDate()));
        }
    }
    
    protected BigDecimal randomAmount() {
        return BigDecimal.valueOf(RandomUtils.nextDouble() * 1000);
    }
    
    protected Date randomDate() {
        return DateUtils.addDays(NOW, -Math.abs(RandomUtils.nextInt(1000)));
    }
}
