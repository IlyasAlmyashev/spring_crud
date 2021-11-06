package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping(value="/users")
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getUsers(Model model){
        model.addAttribute("list",userService.getAllUsers());
        return "allUsers";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("user",userService.getUserById(id));
        return "user";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable(name = "id") Long id){
        userService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/new")
    public String newUserForm(Model model){
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/add")
    public String createUser(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("user",userService.getUserById(id));
        return "edit";
    }

    @PostMapping(value = "/edit")
    public String update(@ModelAttribute User user){
        userService.updateUser(user);
        return "redirect:/users";
    }
}
