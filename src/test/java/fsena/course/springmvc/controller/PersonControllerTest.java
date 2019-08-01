package fsena.course.springmvc.controller;

import fsena.course.springmvc.model.Person;
import fsena.course.springmvc.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class PersonControllerTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    public void testListPersons() throws Exception {
        List<Person> persons = new ArrayList<>();

        persons.add(new Person());
        persons.add(new Person());

        when(personService.listPersons()).thenReturn(persons);

        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(view().name("person/list"))
                .andExpect(model().attribute("persons", hasSize(2)));
    }

    @Test
    public void testShowPerson() throws Exception {
        Integer id = 1;

        when(personService.getPersonById(id)).thenReturn(new Person());

        mockMvc.perform(get("/person/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("person/show"))
                .andExpect(model().attribute("person", instanceOf(Person.class)));
    }

    @Test
    public void testEditPerson() throws Exception {
        Integer id = 1;

        when(personService.getPersonById(id)).thenReturn(new Person());

        mockMvc.perform(get("/person/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("person/form"))
                .andExpect(model().attribute("person", instanceOf(Person.class)));
    }

    @Test
    public void testNewPerson() throws Exception {
        Integer id = 1;

        verifyZeroInteractions(personService);

        mockMvc.perform(get("/person/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("person/form"))
                .andExpect(model().attribute("person", instanceOf(Person.class)));
    }

    @Test
    public void testSaveOrUpdatePerson() throws Exception {
        Integer id = 1;
        String firstAddress = "Rua dos Bobos";
        String city = "Belo Horizonte";
        String state = "Minas Gerais";
        String firstName = "Fulano";
        String lastName = "Alves";
        String email = "fulano.alves@email.com";
        String phoneNumber = "7070-7070";

        Person returnPerson = createPerson();

        when(personService.saveOrUpdatePerson(any(Person.class))).thenReturn(returnPerson);

        mockMvc.perform(post("/person")
        .param("id", "1")
        .param("firstName", firstName)
        .param("lastName", lastName)
        .param("email", email)
        .param("phoneNumber", phoneNumber)
        .param("firstAddress", firstAddress)
        .param("city", city)
        .param("state", state))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/person/1"))
                .andExpect(model().attribute("person", instanceOf(Person.class)))
                .andExpect(model().attribute("person", hasProperty("id", is(id))))
                .andExpect(model().attribute("person", hasProperty("firstName", is(firstName))))
                .andExpect(model().attribute("person", hasProperty("lastName", is(lastName))))
                .andExpect(model().attribute("person", hasProperty("email", is(email))))
                .andExpect(model().attribute("person", hasProperty("phoneNumber", is(phoneNumber))))
                .andExpect(model().attribute("person", hasProperty("firstAddress", is(firstAddress))))
                .andExpect(model().attribute("person", hasProperty("city", is(city))))
                .andExpect(model().attribute("person", hasProperty("state", is(state))));

        ArgumentCaptor<Person> boundPerson = ArgumentCaptor.forClass(Person.class);
        verify(personService).saveOrUpdatePerson(boundPerson.capture());

        assertEquals(id, boundPerson.getValue().getId());
        assertEquals(firstName, boundPerson.getValue().getFirstName());
        assertEquals(lastName, boundPerson.getValue().getLastName());
        assertEquals(email, boundPerson.getValue().getEmail());
        assertEquals(phoneNumber, boundPerson.getValue().getPhoneNumber());
        assertEquals(firstAddress, boundPerson.getValue().getFirstAddress());
        assertEquals(city, boundPerson.getValue().getCity());
        assertEquals(state, boundPerson.getValue().getState());
    }

    @Test
    public void testDeletePerson() throws Exception {
        Integer id = 1;

        mockMvc.perform(get("/person/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/persons"));

        verify(personService, times(1)).deletePerson(id);
    }

    private Person createPerson() {
        Integer id = 1;
        String firstAddress = "Rua dos Bobos";
        String city = "Belo Horizonte";
        String state = "Minas Gerais";
        String firstName = "Fulano";
        String lastName = "Alves";
        String email = "fulano.alves@email.com";
        String phoneNumber = "7070-7070";

        Person person = Person.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .firstAddress(firstAddress)
                .city(city)
                .state(state)
                .build();

        return person;
    }
}
