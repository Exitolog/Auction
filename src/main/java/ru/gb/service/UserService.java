package ru.gb.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.entity.User;
import ru.gb.exception.UserExistException;
import ru.gb.repository.RoleRepository;
import ru.gb.repository.UserRepository;
import java.util.List;


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
           throw new UserExistException("Пользователь с логином " + user.getLogin() + " уже существует");
       user.setRoles(List.of(roleRepository.findById(2L).get()));
       BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
       String cryptPassword = bCryptPasswordEncoder.encode(user.getPassword());
       user.setPassword(cryptPassword);
       userRepository.saveAndFlush(user);
   }

}
