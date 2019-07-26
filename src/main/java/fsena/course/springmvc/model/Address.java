package fsena.course.springmvc.model;

import lombok.Data;

@Data
public class Address {

    private String firstAddress;

    private String secondAddress;

    private String city;

    private String state;

    private String zipCode;
}
