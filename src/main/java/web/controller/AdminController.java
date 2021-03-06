package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping(name = "/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String getUser(Model model) {
        model.addAttribute("list", userService.getAll());
        return "admin";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable(name = "id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/createForm")
    public String createForm(Model model) {
        model.addAttribute("user", new User());
        return "createUser";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/admin";
    }


}
