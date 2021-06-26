package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping()
    public String showUsers(Model model, Authentication aut) {
        model.addAttribute("user", service.getUserByLogin(aut.getName()));
        return "showUser";
    }


}
