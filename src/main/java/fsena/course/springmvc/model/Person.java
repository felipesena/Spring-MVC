package fsena.course.springmvc.model;

import lombok.Data;

@Data
public class Person {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Address address;
}
