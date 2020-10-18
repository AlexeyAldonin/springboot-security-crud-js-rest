package app.service;

import app.model.User;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailServiceImpl")
public class UserBootService  implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserBootService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(new User());
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.findByName(name);
    }

    /*@Transactional
    @PostConstruct
    public void init(){
        User byName = userRepository.findByName("root");
        if (byName != null){
            if (byName.getPassword().equals("root")){
                return;
            }
            byName.setPassword("root");
            userRepository.save(byName);
            return;
        }
        Set<Role> allRoles = new HashSet<>();
        allRoles.add(new Role("USER"));
        allRoles.add(new Role("ADMIN"));
        User root = new User("root", "root");
        root.setRoles(allRoles);
        userRepository.save(root);
    }*/
}
