package ru.gb.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.h2.engine.Mode;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.entity.User;
import ru.gb.service.UserService;
import ru.gb.service.UserValidationService;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserPageController {

    private final UserService userService;
    private final UserValidationService userValidationService;

    @GetMapping()
    public String getAllUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users-page";
    }


    @GetMapping("/new")
    public String newUser(User user){
//        model.addAttribute("user", new User());
        return "create-user";
    }

//    @PostMapping("/new")
//    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model){
//        if(result.hasErrors()){
//            return "not-found";
//        }
//        userService.createUser(user);
//        return "redirect:/users";
//    }
    
    @PostMapping("/new")
    public String createUser(@Valid User user, BindingResult result, Model model){
        String passErr = userValidationService.validPassword(user);
        String phoneErr = userValidationService.validPhoneNumber(user);
        String loginErr = userValidationService.validLogin(user);
        if(!passErr.isEmpty()) {
            ObjectError passwordError = new ObjectError("passwordError", passErr);
            result.addError(passwordError);
        }
        if(!phoneErr.isEmpty()) {
            ObjectError phoneError = new ObjectError("phoneError", phoneErr);
            result.addError(phoneError);
        }
        if(!loginErr.isEmpty()) {
            ObjectError loginError = new ObjectError("loginError", loginErr);
            result.addError(loginError);
        }
        if(result.hasErrors()){
            return "create-user";
        }
        userService.createUser(user);
        return "redirect:/users";
    }

}
