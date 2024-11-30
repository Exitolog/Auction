package ru.gb.userLogic;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.gb.REST.Publication;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Publication> publicationList = new ArrayList<>();

    public void addPublication(Publication publication) {
        publicationList.add(publication);
    }

}
