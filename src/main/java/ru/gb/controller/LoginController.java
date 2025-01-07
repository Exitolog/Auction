package ru.gb.controller;

import lombok.RequiredArgsConstructor;
import org.h2.engine.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.entity.Publication;
import ru.gb.entity.User;
import ru.gb.service.UserService;

@Controller
public class LoginController {


    @GetMapping("/login")
    public String login(){
        return "login2";
    }



}
