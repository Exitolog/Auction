package ru.gb.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gb.SpringBootTestBase;
import ru.gb.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest extends SpringBootTestBase {

    @Autowired
    private UserRepository userRepository;


    @Test
    void getAllUsers() {

    }

    @Test
    void createUser() {
    }
}