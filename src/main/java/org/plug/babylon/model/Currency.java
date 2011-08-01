package org.plug.babylon.model;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Sryl <cyril.lacote@gmail.com>
 */
@Entity
@Cacheable
@XmlRootElement
public class Currency implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String code;
    
    private String symbol;

    /**
     * Technical constructor
     */
    protected Currency() {
    }

    public Currency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.code).toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Currency)) {
            return false;
        }
        Currency other = (Currency) object;
        return new EqualsBuilder().append(this.code, other.code).isEquals();
    }

    @Override
    public String toString() {
        return "Currency[" + code + "]";
    }
    
}
