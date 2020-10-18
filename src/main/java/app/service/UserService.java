package app.service;

import app.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void save(User user);

    void delete(Long id);

    User getById(Long id);

    void update(User user);

    List<User> getAll();
}
