package org.plug.babylon.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
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
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /** Unique account number */
    @NotNull
    @Column(unique = true)
    private String number;
    
    /** Managed currency for this account */
    @ManyToOne(optional = false)
    @NotNull
    private Currency currency;
    
    /** Managed currency for this account */
    @ManyToOne(optional = false)
    @NotNull
    private Owner owner;

    /**
     * Constructor
     * @param number
     * @param currency
     * @param owner 
     */
    public Account(String number, Currency currency, Owner owner) {
        this.number = number;
        this.currency = currency;
        this.owner = owner;
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
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
