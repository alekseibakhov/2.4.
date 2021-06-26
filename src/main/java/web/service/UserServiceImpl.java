package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDAO;
import web.dao.UserDAO;
import web.model.Role;
import web.model.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO interfaceDAO;
    @Autowired
    RoleDAO roleDAO;
    PasswordEncoder encoder;

    @Override
    @Transactional
    public List<User> getAll() {
        return interfaceDAO.getAll();
    }

    @Override
    @Transactional
    public User getById(int id) {
        return interfaceDAO.getById(id);
    }

    @Override
    @Transactional
    public void save(User person) {
        Set<Role> roleSet = new HashSet<>();
        User user = new User();
        user.setPassword(encoder.encode(person.getPassword()));
        user.setAge(person.getAge());
        user.setId(person.getId());
        user.setName(person.getName());
        interfaceDAO.save(user);
    }

    @Override
    @Transactional
    public void update(int id, User person) {
        interfaceDAO.update(id, person);
    }

    @Override
    @Transactional
    public void delete(int id) {
        interfaceDAO.delete(id);
    }

    @Override
    public User getUserByLogin(String name) {
        return interfaceDAO.getByName(name);
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoleSet()));
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }

}
