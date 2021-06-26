package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImplem implements UserDAO {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Override
    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getByName(String name) {
        return entityManager.find(User.class, name);
    }

    @Override
    public void save(User person) {
        entityManager.persist(person);
    }

    @Override
    public void update(int id, User person) {
        entityManager.merge(person);

    }

    @Override
    public void delete(int id) {
        entityManager.remove(getById(id));
    }
}
