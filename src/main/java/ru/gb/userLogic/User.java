package ru.gb.userLogic;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ru.gb.REST.Publication;

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

    @Size(min = 6, max = 16, message = "Логин должен быть от 6 до 16 символов")
    @Column(name = "login")
    private String login;

    @Size(min = 8, max = 16, message = "Пароль должен быть от 8 до 16 символов")
    @Column(name = "password")
    private String password;

    @Transient
    private String confirmPassword;

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
