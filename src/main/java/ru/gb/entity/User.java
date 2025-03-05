package ru.gb.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

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

    @OneToMany(mappedBy = "holder", fetch = FetchType.EAGER)
    private List<Publication> publicationList = new ArrayList<>();

    public void addPublication(Publication publication) {
        publicationList.add(publication);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("login='" + login + "'")
                .add("password='" + password + "'")
                .add("confirmPassword='" + confirmPassword + "'")
                .add("phoneNumber='" + phoneNumber + "'")
                .toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        User user = (User) object;
        return Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles) && Objects.equals(publicationList, user.publicationList);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(login);
        result = 31 * result + Objects.hashCode(password);
        result = 31 * result + Objects.hashCode(roles);
        result = 31 * result + Objects.hashCode(publicationList);
        return result;
    }
}
