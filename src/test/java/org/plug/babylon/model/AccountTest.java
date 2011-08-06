package org.plug.babylon.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Account}
 * @author Sryl <cyril.lacote@gmail.com>
 */
public class AccountTest {

    /**
     * Test of addOperation method, of class Account.
     */
    @Test
    public void testAddOperation() {
        
        Owner o = new Owner("toto", "toto@toto.com");
        
        // Account initialAmount = 0;
        Account a = new Account("test", Currency.getInstance("EUR"), o, BigDecimal.ZERO);
        
        a.addOperation(new Ope(BigDecimal.TEN, new Date()));
        a.addOperation(new Ope(BigDecimal.ONE.negate(), new Date()));
        
        assertEquals(BigDecimal.valueOf(0), a.getInitialBalance());
        assertEquals(BigDecimal.valueOf(0), a.getConsolidatedBalance());
        assertEquals(BigDecimal.valueOf(0+10-1), a.getCurrentBalance());
    }
}
