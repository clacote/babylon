package org.plug.babylon.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * A transfer operation, from an account to an other
 * @author Sryl <cyril.lacote@gmail.com>
 */
@Entity
public class Transfer extends Ope {
    private static final long serialVersionUID = 1L;

    /** Destination account for this transfer */
    // Hibernate JPA2 isn't smart enough to accept @ManyToOne(optional=false) in this child entity : column became NOT NULL in BDD
    @ManyToOne
    @NotNull
    private Account destination;

    public Account getDestination() {
        return destination;
    }

    public void setDestination(Account destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Transfer [ id=" + getId() + ", of " + getAmount() + " from " + getAccount() + " to " + getDestination() + " ]";
    }
}
