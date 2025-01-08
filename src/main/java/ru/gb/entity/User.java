package ru.gb.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 6, max = 16)
    @NotEmpty(message = "Логин не может быть пустым и должен быть минимум 6 символов")
    @Column(name = "login")
    private String login;

    @Size(min = 8, max = 75)
    @NotEmpty(message = "Пароль не может быть пустым и должен быть минимум 8 символов")
    @Column(name = "password")
    private String password;

    @Transient
    @NotEmpty(message = "Пароль не может быть пустым и должен совпадать с паролем выше")
    private String confirmPassword;

    @Column(name = "phoneNumber")
    @NotEmpty(message = "Телефон не может быть пустым")
    private String phoneNumber;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Publication> publicationList = new ArrayList<>();

    public void addPublication(Publication publication) {
        publicationList.add(publication);
    }

}
