package org.plug.babylon.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * An account
 * @author Sryl <cyril.lacote@gmail.com>
 */
@Entity
@Cacheable
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /** Unique account number */
    @NotNull
    @Column(unique = true)
    private String number;
    
    /**
     * Managed currency for this account.
     * Must be an ISO currency code, cf. {@link Currency#getInstance(java.lang.String)}
     */
    @NotNull
    private String currencyCode;
    
    /** Owner of this account */
    @ManyToOne(optional = false)
    @NotNull
    private Owner owner;
    
    /** Initial balance when account opened */
    private BigDecimal initialBalance;
    
    /** Current balance with all known statements = initialBalance + sum(statements.amount) */
    private BigDecimal currentBalance;
    
    /** Balance with all consolidated statements = initialBalance + sum(statements.amount where statement.state == Consolidated) */
    private BigDecimal consolidatedBalance;
    
    /**
     * Constructor
     * @param number
     * @param currency
     * @param owner 
     * @param initialBalance 
     */
    public Account(String number, Currency currency, Owner owner, BigDecimal initialBalance) {
        this.number = number;
        this.currencyCode = currency.getCurrencyCode();
        this.owner = owner;
        this.initialBalance = initialBalance;
        this.consolidatedBalance = initialBalance;
        this.currentBalance = initialBalance;
    }

    // FIXME ACIDity? synchronized?
    public void addOperation(Ope operation) {
        operation.setAccount(this);
        if (operation.getAmount() != null) {
            currentBalance = currentBalance.add(operation.getAmount());
        }
    }
    
    /** Technical constructor */
    protected Account() {
    }

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrency(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public BigDecimal getConsolidatedBalance() {
        return consolidatedBalance;
    }

    void setConsolidatedBalance(BigDecimal consolidatedBalance) {
        this.consolidatedBalance = consolidatedBalance;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        return new EqualsBuilder().append(this.id, other.id).isEquals();
    }

    @Override
    public String toString() {
        return "Account[ id=" + id + " ]";
    }
}
