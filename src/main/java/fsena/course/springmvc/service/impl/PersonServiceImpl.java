package fsena.course.springmvc.service.impl;

import fsena.course.springmvc.model.Address;
import fsena.course.springmvc.model.Person;
import fsena.course.springmvc.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonServiceImpl implements PersonService {

    private Map<Integer, Person> persons;

    public PersonServiceImpl() {
        loadPersons();
    }

    private void loadPersons() {
        persons = new HashMap<>();

        Address address = Address.builder()
                .city("Belo Horizonte")
                .firstAddress("Rua dos Bobos")
                .state("Minas Gerais")
                .zipCode("99999")
                .build();

        Person person1 = Person.builder()
                .id(1)
                .firstName("Fulano")
                .lastName("da Silva")
                .address(address)
                .email("email@hotmail.com")
                .phoneNumber("(31) 3333-3333")
                .build();

        persons.put(person1.getId(), person1);

        Person person2 = Person.builder()
                .id(2)
                .firstName("Siclano")
                .lastName("Alves")
                .address(address)
                .email("email@hotmail.com")
                .phoneNumber("(31) 3333-3333")
                .build();

        persons.put(person2.getId(), person2);
    }

    private Integer getNextKey() {
        return Collections.max(persons.keySet()) + 1;
    }

    @Override
    public List<Person> listPersons() {
        return new ArrayList<>(persons.values());
    }

    @Override
    public Person getPersonById(int id) {
        return persons.get(id);
    }

    @Override
    public Person saveOrUpdatePerson(Person person) {
        if (person != null) {
            if (person.getId() == null) {
                person.setId(getNextKey());
            }

            persons.put(person.getId(), person);

            return person;
        } else {
            throw new RuntimeException("Person cannot be null");
        }
    }

    @Override
    public void deletePerson(int id) {
        persons.remove(id);
    }
}
