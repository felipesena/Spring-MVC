package fsena.course.springmvc.services.mapservices;

import fsena.course.springmvc.domain.DomainObject;
import fsena.course.springmvc.domain.User;
import fsena.course.springmvc.services.UserService;

import java.util.List;

public class UserServiceMapImpl extends AbstractMapService implements UserService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public User getById(Integer id) {
        return (User) super.getById(id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        return (User) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }
}
