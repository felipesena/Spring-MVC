package fsena.course.springmvc.service.impl;

import fsena.course.springmvc.config.JpaIntegrationConfig;
import fsena.course.springmvc.model.Person;
import fsena.course.springmvc.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class PersonServiceJpaDaoImplTest {

    private PersonService personService;

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @Test
    public void testListMethod() {
        List<Person> persons = personService.listPersons();

        assert persons.size() == 5;
    }
}
