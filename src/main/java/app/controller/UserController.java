package app.controller;

import app.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping(value = {"/", "/user"})
    public String getUserView(ModelMap modelMap) {
        UserDetails userDetails = (User) SecurityContextHolder
                                                              .getContext()
                                                              .getAuthentication()
                                                              .getPrincipal();
        modelMap.addAttribute("user", userDetails);
        return "user";
    }
}
