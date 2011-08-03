package org.plug.babylon.service.importer;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.ResponseEnvelope;
import net.sf.ofx4j.domain.data.banking.BankStatementResponse;
import net.sf.ofx4j.domain.data.banking.BankStatementResponseTransaction;
import net.sf.ofx4j.domain.data.banking.BankingResponseMessageSet;
import net.sf.ofx4j.domain.data.common.Transaction;
import net.sf.ofx4j.io.AggregateUnmarshaller;
import org.junit.Test;

/**
 * Tests of using OFX4J library (OFX files management)
 * @author Sryl <cyril.lacote@gmail.com>
 */
public class OFX4JTest {

    private InputStream getSampleFile() throws FileNotFoundException {
        return getClass().getResourceAsStream("sample.ofx");
    }

    @Test
    public void testUnMarshall() throws Exception {
        //Use ResponseEnvelope to start.
        AggregateUnmarshaller<ResponseEnvelope> unmarshaller = new AggregateUnmarshaller<ResponseEnvelope>(ResponseEnvelope.class);

        ResponseEnvelope envelope = unmarshaller.unmarshal(getSampleFile());
 
        //Assume we are just interested in the banking statements.  Make sure to cast.
        BankingResponseMessageSet messageSet = (BankingResponseMessageSet) envelope.getMessageSet(MessageSetType.banking);

        List<BankStatementResponseTransaction> responses = messageSet.getStatementResponses();
        for (BankStatementResponseTransaction response : responses) {
            BankStatementResponse message = response.getMessage();
            String currencyCode = message.getCurrencyCode();
            List<Transaction> transactions = message.getTransactionList().getTransactions();
            for (Transaction transaction : transactions) {
                System.out.println(transaction.getName() + " " + transaction.getAmount() + " "
                        + currencyCode);
            }
        }
    }
}
