//package ru.gb.service;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import ru.gb.SpringBootTestBase;
//import ru.gb.entity.User;
//import ru.gb.repository.UserRepository;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class UserServiceTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserService userService;
//
//
//    @Test
//    void getAllUsers() {
//
//    }
//
//    @Test
//    void tesCreateUser(User user) {
//        User user1 = new User();
//        user1.setLogin("Sergey");
//        user1.setPassword("sergey_password");
//        user1.setConfirmPassword("sergey_password");
//        user1.setPhoneNumber("+79131951099");
//        userService.createUser(user1);
//
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            userService.createUser(user);
//        });
//        assertEquals("Пользователь с логином Sergey уже существует", exception.getMessage());
//    }
//}