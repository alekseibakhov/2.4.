package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAll();

    public User getById(int id);
    public User getByName(String name);

    public void save(User person);

    public void update(int id, User person);

    public void delete(int id);
}
