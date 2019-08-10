package fsena.course.springmvc.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Product extends AbstractDomainClass {

    private String description;

    private BigDecimal price;

    private String imageUrl;
}
