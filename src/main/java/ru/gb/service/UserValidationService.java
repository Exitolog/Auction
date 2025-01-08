package ru.gb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.entity.User;
import ru.gb.repository.UserRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserValidationService {

    private final UserRepository userRepository;

    public String validPassword(User user){
        if(!Objects.equals(user.getConfirmPassword(), user.getPassword())) {
            return "Пароли не совпадают";
        }
        else return "";
    }

    public String validPhoneNumber(User user){
        String message = "";
        if(user.getPhoneNumber().length() > 12 || user.getPhoneNumber().length() < 11) return message + "Введен несуществующий номер";
        if(!user.getPhoneNumber().matches("^(89|\\+79)\\d{9}$")) return message + "Некорректно введен номер. Правильный формат +79999999999";
        return message;
    }

    public String validLogin(User user){
        if(userRepository.findByLogin(user.getLogin()).isPresent()) return "Пользователь с логином " + user.getLogin() + " уже существует";
        return "";
    }


}
