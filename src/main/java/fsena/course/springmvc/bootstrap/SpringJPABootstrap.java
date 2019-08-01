package fsena.course.springmvc.bootstrap;

import fsena.course.springmvc.model.Person;
import fsena.course.springmvc.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final PersonService personService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadPersons();
    }

    private void loadPersons() {
        personService.saveOrUpdatePerson(createPerson("Fulano", "da Silva"));
        personService.saveOrUpdatePerson(createPerson("João", "Batista"));
        personService.saveOrUpdatePerson(createPerson("Siclano", "Alves"));
        personService.saveOrUpdatePerson(createPerson("Pedro", "Mendonça"));
        personService.saveOrUpdatePerson(createPerson("Sabrina", "Melo"));
    }

    private Person createPerson(String firstName, String lastName) {
        Person person = Person.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(firstName.toLowerCase() + "@email.com")
                .phoneNumber("(31) 3333-3333")
                .city("Belo Horizonte")
                .firstAddress("Rua dos Bobos")
                .state("Minas Gerais")
                .zipCode("99999")
                .build();

        return person;
    }
}
