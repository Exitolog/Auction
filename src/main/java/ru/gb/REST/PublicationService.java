package ru.gb.REST;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.userLogic.User;
import ru.gb.userLogic.UserRepository;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublicationService {

    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;

    // убрать оптионал
    public Optional<Publication> findById(Long id) {
        return publicationRepository.findById(id);
    }

    //    public Optional<List<Publication>> findAllByClient(Long id){
//        return Optional.of(publicationRepository.findAllByClientId(id));
//    }
    // убрать оптионал
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь c id " + id + " не найден"));
    }

    public User findUserByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Пользователь c логином " + login + " не найден"));
    }

    public Optional<List<Publication>> findAll() {
        return Optional.of(publicationRepository.findAll());
    }

    public Publication create(Publication publication) {
        if (Objects.isNull(publication.getCategory())) {
            throw new IllegalArgumentException("Категория не может быть null");
        }
        if (Objects.isNull(publication.getCondition())) {
            throw new IllegalArgumentException("Состояние не может быть null");
        }
        return publicationRepository.save(publication);
    }

    public void delete(Long id) {
        publicationRepository.deleteById(id);
    }

}
