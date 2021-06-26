package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDAOImplem implements RoleDAO {
    @PersistenceContext(name = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public Role getRole(int id) {
        return entityManager.find(Role.class, id);

    }
}
