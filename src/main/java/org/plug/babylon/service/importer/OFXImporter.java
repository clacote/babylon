package org.plug.babylon.service.importer;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.ResponseEnvelope;
import net.sf.ofx4j.domain.data.banking.BankAccountDetails;
import net.sf.ofx4j.domain.data.banking.BankStatementResponse;
import net.sf.ofx4j.domain.data.banking.BankStatementResponseTransaction;
import net.sf.ofx4j.domain.data.banking.BankingResponseMessageSet;
import net.sf.ofx4j.domain.data.common.Transaction;
import net.sf.ofx4j.io.AggregateUnmarshaller;
import net.sf.ofx4j.io.OFXParseException;
import org.plug.babylon.model.Account;
import org.plug.babylon.model.Ope;
import org.plug.babylon.service.AccountService;
import org.plug.babylon.service.OperationService;

/**
 * OFX data importer
 * @author Sryl <cyril.lacote@gmail.com>
 */
@Stateless(name="ofxImporter")
public class OFXImporter implements Importer {
    
    @EJB
    private OperationService operationService;
    
    @EJB
    private AccountService accountService;
    
    @Override
    public void importData(InputStream data) {
        AggregateUnmarshaller<ResponseEnvelope> unmarshaller = new AggregateUnmarshaller<ResponseEnvelope>(ResponseEnvelope.class);

        ResponseEnvelope envelope = null;
        try {
            envelope = unmarshaller.unmarshal(data);

        } catch (IOException ex) {
            Logger.getLogger(OFXImporter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OFXParseException ex) {
            Logger.getLogger(OFXImporter.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        if (envelope != null) {
            
            List<Ope> operations = new ArrayList<Ope>();

            //Assume we are just interested in the banking statements.  Make sure to cast.
            BankingResponseMessageSet messageSet = (BankingResponseMessageSet) envelope.getMessageSet(MessageSetType.banking);

            List<BankStatementResponseTransaction> responses = messageSet.getStatementResponses();
            for (BankStatementResponseTransaction response : responses) {
                BankStatementResponse message = response.getMessage();
                String currencyCode = message.getCurrencyCode();
                
                Account account = createAccount(message.getAccount(), currencyCode);
                
                List<Transaction> transactions = message.getTransactionList().getTransactions();
                for (Transaction transaction : transactions) {
                    System.out.println(transaction.getName() + " " + transaction.getAmount() + " "
                            + currencyCode);
                    
                    operations.add(createOperation(account, transaction));
                }
            }
            
            for (Ope operation : operations) {
                accountService.create(operation.getAccount());
                operationService.create(operation);
            }
        }
    }
    
    private Account createAccount(BankAccountDetails ofxData, String currencyCode) {
        return new Account(ofxData.getAccountNumber(), Currency.getInstance(currencyCode), null);
    }
    
    private Ope createOperation(Account account, Transaction ofxData) {
        return new Ope( account, BigDecimal.valueOf(ofxData.getAmount()), ofxData.getDateAvailable());
    }
}
