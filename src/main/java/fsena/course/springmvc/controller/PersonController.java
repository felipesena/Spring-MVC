package fsena.course.springmvc.controller;

import fsena.course.springmvc.model.Person;
import fsena.course.springmvc.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class PersonController {

    private final PersonService personService;

    @GetMapping("/persons")
    public String listPersons(Model model) {
        model.addAttribute("persons", personService.listPersons());

        return "person/list";
    }

    @GetMapping("/person/{id}")
    public String getPerson(@PathVariable int id, Model model) {
        model.addAttribute("person", personService.getPersonById(id));

        return "person/show";
    }

    @GetMapping("/person/edit/{id}")
    public String editPerson(@PathVariable int id, Model model) {
        model.addAttribute("person", personService.getPersonById(id));

        return "person/form";
    }

    @GetMapping("/person/new")
    public String newPerson(Model model) {
        model.addAttribute("person", Person.builder().build());

        return "person/form";
    }

    @PostMapping("/person")
    public String saveOrUpdatePerson(Person person) {
        Person savedPerson = personService.saveOrUpdatePerson(person);

        return "redirect:/person/" + savedPerson.getId();
    }

    @GetMapping("/person/delete/{id}")
    public String deletePerson(@PathVariable int id) {
        personService.deletePerson(id);

        return "redirect:/persons";
    }
}
