package fsena.course.springmvc.service.impl;

import fsena.course.springmvc.model.Person;
import fsena.course.springmvc.service.PersonService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Service
@Profile("jpadao")
public class PersonServiceJpaDaoImpl implements PersonService {

    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf =  emf;
    }

    @Override
    public List<Person> listPersons() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Person", Person.class).getResultList();
    }

    @Override
    public Person getPersonById(int id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Person.class, id);
    }

    @Override
    public Person saveOrUpdatePerson(Person person) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Person savedPerson = em.merge(person);
        em.getTransaction().commit();

        return savedPerson;
    }

    @Override
    public void deletePerson(int id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Person.class, id));
        em.getTransaction().commit();
    }
}
