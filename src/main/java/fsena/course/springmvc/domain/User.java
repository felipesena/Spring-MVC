package fsena.course.springmvc.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class User extends AbstractDomainClass {

    private String username;

    @Transient
    private String password;

    private String encryptedPassword;

    private Boolean enabled = true;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    public void setCustomer(Customer customer) {
        this.customer = customer;
        this.customer.setUser(this);
    }
}
