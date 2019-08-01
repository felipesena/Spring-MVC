package fsena.course.springmvc.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

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
