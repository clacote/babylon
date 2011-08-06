package org.plug.babylon.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * An accounts' owner
 * @author Sryl <cyril.lacote@gmail.com>
 */
@Entity
@NamedQueries({
    @NamedQuery(name=Owner.FIND_BY_EMAIL, query="select o from Owner o where o.email = :email")
})
public class Owner implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String PREFIX = "Owner.";
    public static final String FIND_BY_EMAIL = PREFIX + "findByEmail";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    private String email;

    public Owner(String name, String email) {
        this.name = name;
        this.email = email;
    }

    
    /** Technical constructor */
    protected Owner() {}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.id).toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Owner)) {
            return false;
        }
        Owner other = (Owner) object;
        return new EqualsBuilder().append(this.id, other.id).isEquals();
    }

    @Override
    public String toString() {
        return "Owner[ id=" + id + " ]";
    }
    
}
