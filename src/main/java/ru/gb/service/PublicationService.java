package ru.gb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.repository.PublicationRepository;
import ru.gb.entity.Publication;
import ru.gb.entity.User;
import ru.gb.repository.UserRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublicationService {

    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;

    public Publication findById(Long id) {
        return publicationRepository.findById(id).orElse(null);
    }


    public List<Publication> findAll() {
        return publicationRepository.findAll();
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
