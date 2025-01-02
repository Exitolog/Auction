package ru.gb.controller;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.entity.User;
import ru.gb.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserPageController {

    private final UserService userService;

    @GetMapping()
    public String getAllUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users-page";
    }


    @GetMapping("/new")
    public String newUser(@NotNull Model model){
        model.addAttribute("user", new User());
        return "create-user";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user){
        userService.createUser(user);
        return "redirect:/users";
    }

}
