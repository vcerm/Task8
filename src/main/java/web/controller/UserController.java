package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import web.Service.UserService;
import web.models.User;

@Controller
@RequestMapping("/Users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String allUsers(Model model) {
        model.addAttribute("Users", userService.getAllUsers());
        return "Users/AllUsers";
    }

    @GetMapping("/NewUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "Users/NewUser";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/Users";
    }

    @DeleteMapping("/{id}")
    public String removeUserById(@PathVariable("id") int id) {
        userService.removeUserById(id);
        return "redirect:/Users";
    }

    @GetMapping("/{id}/UpdateUser")
    public String edit (@ModelAttribute("id") int id,Model model){
        model.addAttribute("user",userService.getUserById(id));
        return "Users/UpdateUser";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user")@Validated User updateuser, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "Users/UpdateUser";
        }
        userService.updateUser(updateuser);
        return "redirect:/Users";
    }

}
