package ru.gb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.entity.Publication;
import ru.gb.repository.PublicationRepository;
import ru.gb.model.PublicationPage;
import ru.gb.entity.User;
import ru.gb.repository.UserRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublicationPageService {

    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;


    public List<PublicationPage> findAll() {
        return publicationRepository.findAll()
                .stream()
                .map(this::convertToPage)
                .toList();
    }

    public User findUserByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Пользователь c логином " + login + " не найден"));
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь c id " + id + " не найден"));
    }

    public PublicationPage findPublicationPageById(Long id) {
        return publicationRepository.findById(id).map(this::convertToPage).orElse(null);
    }

    public Publication findPublication(Long id){
        return publicationRepository.findById(id).orElse(null);
    }

    public void create(Publication publication) {
        if (Objects.isNull(publication.getUser())) {
            User user = new User();
            user.setLogin("Нет ставок");
            user.setPassword("$2a$12$mT1uu45eeoYNuAQoDCxQTuc29uI3qi50RFGwn22/65duAA0Ycust2");
            user.setConfirmPassword("$2a$12$mT1uu45eeoYNuAQoDCxQTuc29uI3qi50RFGwn22/65duAA0Ycust2");
            user.setPhoneNumber("+79131951099");
            userRepository.save(user);
            publication.setUser(user);
        }
        publication.setPriceNow(1L);
        publicationRepository.save(publication);
        convertToPage(publication);
    }

    public void update(Long id, Publication publication) {
        Publication publicationToBeUpdated = publicationRepository.findById(id).get();
        publicationToBeUpdated.setCategory(publication.getCategory());
        publicationToBeUpdated.setDateOfFinishTrade(publication.getDateOfFinishTrade());
        publicationToBeUpdated.setCondition(publication.getCondition());
        publicationToBeUpdated.setDescriptionPublication(publication.getDescriptionPublication());
        publicationRepository.save(publicationToBeUpdated);
    }

    // сделать свое исключение, ловить его хендлером , возврпащть на страницу нрмальную ошибку
    public void delete(Long id) {
        if (publicationRepository.findById(id).isPresent()) publicationRepository.deleteById(id);
        else throw new RuntimeException("Лота с идентификатором " + id + " не существует");
    }

    // почитать про маппер
    private PublicationPage convertToPage(Publication publication) {
        PublicationPage publicationPage = new PublicationPage();
        publicationPage.setCategory(publication.getCategory().name());
        publicationPage.setId(String.valueOf(publication.getId()));
        publicationPage.setDatePublication(publication.getDatePublication());
        publicationPage.setStatusPublication(publication.getStatusPublication().getStatusName());
        publicationPage.setCondition(String.valueOf(publication.getCondition()));
        publicationPage.setStartPrice(String.valueOf(publication.getStartPrice()));
        publicationPage.setPriceNow(String.valueOf(publication.getPriceNow()));
        publicationPage.setUser(publication.getUser().getLogin());
        publicationPage.setHolder(publication.getHolder().getLogin());
        publicationPage.setPhoneHolder(publication.getHolder().getPhoneNumber());
        publicationPage.setLoginUser(publication.getUser().getLogin());
        publicationPage.setDateOfFinishTrade(publication.getDateOfFinishTrade());
        publicationPage.setDescriptionPublication(publication.getDescriptionPublication());
        return publicationPage;
    }

    public void upPrice(Publication publication, Long newPrice, User user) {
        if(publication.getPriceNow() >= newPrice) {
            throw new RuntimeException("Новая ставка не может быть меньше, либо равна текущей");
        }
        publication.setPriceNow(newPrice);
        publication.setUser(user);
        publicationRepository.save(publication);
    }
}

