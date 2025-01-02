package ru.gb.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.gb.userLogic.Role;
import ru.gb.userLogic.User;
import ru.gb.userLogic.UserRepository;

import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            User user = userRepository.findByLogin(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

            List<SimpleGrantedAuthority> roles = user.getRoles()
                    .stream().map(it -> new SimpleGrantedAuthority(it.getRoleName()))
                    .toList();

            return new org.springframework.security.core.userdetails.User(
                    user.getLogin(),
                    user.getPassword(),
                    roles);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Пользователь не найден на моменте выгрузки");
        }
    }
}
