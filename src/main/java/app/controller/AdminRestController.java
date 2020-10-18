package app.controller;

import app.model.Role;
import app.model.User;
import app.service.RoleBootService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AdminRestController {

    private final UserService userService;
    private final RoleBootService roleBootService;

    @Autowired
    public AdminRestController(UserService userService, RoleBootService roleBootService) {
        this.userService = userService;
        this.roleBootService = roleBootService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<Iterable<User>> getUsers() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/roles")
    public ResponseEntity<Iterable<Role>> getRoles() {
        return ResponseEntity.ok(roleBootService.getAll());
    }

    @GetMapping(value = "/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().body("User with id=" + id + " deleted");
    }

    @PostMapping(value = "/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody Map<String, Object> payload) {
        User user = new User();
        user.setId(Long.valueOf(String.valueOf(payload.get("id"))));
        fillUser(payload, user);
        userService.update(user);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/saveNewUser")
    public ResponseEntity<User> saveNewUser(@RequestBody Map<String, Object> payload){
        User user = new User();
        fillUser(payload, user);
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    private void fillUser(Map<String, Object> payload, User user) {
        user.setName(String.valueOf(payload.get("name")));
        user.setAge(Integer.parseInt(String.valueOf(payload.get("age"))));
        user.setPassword(String.valueOf(payload.get("password")));
        Iterable<String> roles = (Iterable<String>) payload.get("roles");
        StringBuilder stringBuilder = new StringBuilder();
        roles.forEach(role -> stringBuilder.append(role).append(" "));
        user.setRoles(stringBuilder.toString());
    }
}
