package app.controller;

import app.model.Role;
import app.model.User;
import app.service.RoleBootService;
import app.service.UserBootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    private final UserBootService userService;
    private final RoleBootService roleService;

    @Autowired
    public AdminController(UserBootService userService, RoleBootService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/test")
    public String test() {
        return "test";
    }

    @GetMapping(value = "/admin")
    public String getAllUsers(ModelMap model) {
        UserDetails userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("authorizedUser", userDetails);
        model.addAttribute("newUser", new User());
        List<User> listUsers = userService.getAll();
        Set<Role> roles = roleService.getAll();
        model.addAttribute("allRoles", roles);
        model.addAttribute("users", listUsers);
        return "main";
    }

    @PostMapping(value = "/createUser")
    public String addUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/admin";
    }
    @PostMapping(value = "/delete")
    public String delete(@ModelAttribute User user) {
        userService.delete(user.getId());
        return "redirect:/admin";
    }

    @PostMapping("/deleteUser1/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute User user) {
        userService.update(user);
        return "redirect:/admin";
    }
}
