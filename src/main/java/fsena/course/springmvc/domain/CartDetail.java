package fsena.course.springmvc.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class CartDetail extends AbstractDomainClass {

    private Integer quantity;

    @ManyToOne
    private Cart cart;

    @OneToOne
    private Product product;
}
