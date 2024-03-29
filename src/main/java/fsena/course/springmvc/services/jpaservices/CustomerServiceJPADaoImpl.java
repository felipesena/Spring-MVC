package fsena.course.springmvc.services.jpaservices;

import fsena.course.springmvc.domain.Customer;
import fsena.course.springmvc.services.CustomerService;
import fsena.course.springmvc.services.security.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Service
@Profile("jpadao")
public class CustomerServiceJPADaoImpl extends AbstractJpaDaoService implements CustomerService {

    private final EncryptionService encryptionService;

    @Override
    public List<Customer> listAll() {
        EntityManager em = emf.createEntityManager();

        List<Customer> customers = em.createQuery("from Customer", Customer.class).getResultList();
        em.close();
        return customers;
    }

    @Override
    public Customer getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        Customer customer = em.find(Customer.class, id);
        em.close();
        return customer;
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        if (domainObject.getUser() != null && domainObject.getUser().getPassword() != null) {
            domainObject.getUser().setEncryptedPassword(
                    encryptionService.encryptString(domainObject.getUser().getPassword()));
        }

        Customer savedCustomer = em.merge(domainObject);
        em.getTransaction().commit();
        em.close();

        return savedCustomer;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Customer.class, id));
        em.getTransaction().commit();
        em.close();
    }
}
