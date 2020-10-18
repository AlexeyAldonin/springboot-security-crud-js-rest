package app.config.init;

import app.model.Role;
import app.model.Roles;
import app.model.User;
import app.repository.RoleRepository;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> roleNames = roleRepository.findAll()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList());
        for (Roles role : Roles.values()) {
            if (!roleNames.contains(role.name())){
                roleRepository.save(new Role(role.name()));
            }
        }
        List<Role> allRoles = roleRepository.findAll();
        User byName = userRepository.findByName("root");
        if (byName != null){
            if (byName.getPassword().equals("root")){
                return;
            }
            byName.setPassword("root");
            userRepository.save(byName);
            return;
        }
        User root = new User("root", "root");
        root.setRoles((new HashSet<>(allRoles)));
        userRepository.save(root);
    }
}
