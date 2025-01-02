package ru.gb.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.entity.Role;
import ru.gb.entity.User;
import ru.gb.repository.RoleRepository;
import ru.gb.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

   private final UserRepository userRepository;
   private final RoleRepository roleRepository;


   public List<User> getAllUsers(){
    return userRepository.findAll();
   }

   public void createUser(@NotNull User user){
       if(userRepository.findByLogin(user.getLogin()).isPresent())
           throw new RuntimeException("Пользователь с логином " + user.getLogin() + " уже существует");
       user.setRoles(List.of(roleRepository.findById(2L).get()));
       BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
       String cryptPassword = bCryptPasswordEncoder.encode(user.getPassword());
       user.setPassword(cryptPassword);
       userRepository.save(user);
   }

}
