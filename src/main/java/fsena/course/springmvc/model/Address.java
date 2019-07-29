package fsena.course.springmvc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {

    private String firstAddress;

    private String secondAddress;

    private String city;

    private String state;

    private String zipCode;
}
