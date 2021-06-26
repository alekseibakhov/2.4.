package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAll();

    public User getById(int id);

    public void save(User person);
    public void update(int id, User person);

    public void delete(int id);
    public User getUserByLogin(String name);

}
