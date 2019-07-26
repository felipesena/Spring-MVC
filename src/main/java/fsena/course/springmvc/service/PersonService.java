package fsena.course.springmvc.service;

import fsena.course.springmvc.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> listPersons();

    Person getPersonById(int id);

    Person saveOrUpdatePerson(Person person);

    void deletePerson(int id);
}
