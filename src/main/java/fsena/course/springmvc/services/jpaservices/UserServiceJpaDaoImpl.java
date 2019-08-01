package fsena.course.springmvc.services.jpaservices;

import fsena.course.springmvc.domain.User;
import fsena.course.springmvc.services.UserService;
import fsena.course.springmvc.services.security.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Service
@Profile("jpadao")
public class UserServiceJpaDaoImpl extends AbstractJpaDaoService implements UserService {

    private final EncryptionService encryptionService;

    @Override
    public List<User> listAll() {
        EntityManager em = emf.createEntityManager();

        List<User> users = em.createQuery("from User", User.class).getResultList();
        em.close();
        return users;
    }

    @Override
    public User getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        User user = em.find(User.class, id);
        em.close();
        return user;
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        if(domainObject.getPassword() != null){
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
        }

        User savedUser = em.merge(domainObject);
        em.getTransaction().commit();
        em.close();

        return savedUser;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(User.class, id));
        em.getTransaction().commit();
        em.close();
    }
}
