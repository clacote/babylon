package org.plug.babylon.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * An Operation. "operation" is an SQL reserved keyword.
 * @author Sryl <cyril.lacote@gmail.com>
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@XmlRootElement
public class Ope implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @NotNull
    private Account account;
    
    @NotNull
    private BigDecimal amount;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date operationDate;
    
    private String description;

    public Ope(BigDecimal amount, Date operationDate) {
        this.amount = amount;
        this.operationDate = operationDate;
    }

    public Ope(BigDecimal amount, Date operationDate, String description) {
        this.amount = amount;
        this.operationDate = operationDate;
        this.description = description;
    }
    
    /** Technical constructor */
    protected Ope() {}
    
    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    void setAccount(Account account) {
        this.account = account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.id).toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Ope)) {
            return false;
        }
        Ope other = (Ope) object;
        return new EqualsBuilder().append(this.id, other.id).isEquals();
    }

    @Override
    public String toString() {
        return "Operation[ id=" + id + " ]";
    }
    
}
