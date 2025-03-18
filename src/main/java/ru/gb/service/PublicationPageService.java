package ru.gb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.entity.Publication;
import ru.gb.entity.enums.StatusPublication;
import ru.gb.exception.NoValidUpPriceException;
import ru.gb.exception.PublicationNotFoundException;
import ru.gb.exception.UserNotFoundException;
import ru.gb.repository.PublicationRepository;
import ru.gb.model.PublicationPage;
import ru.gb.entity.User;
import ru.gb.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Service
@Slf4j
public class PublicationPageService {

    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;
    private final User nullUser;

    public PublicationPageService(PublicationRepository publicationRepository, UserRepository userRepository) {
        this.publicationRepository = publicationRepository;
        this.userRepository = userRepository;
        nullUser = new User();
        nullUser.setLogin("Нет ставок");
        nullUser.setPassword("$2a$12$mT1uu45eeoYNuAQoDCxQTuc29uI3qi50RFGwn22/65duAA0Ycust2");
        nullUser.setConfirmPassword("$2a$12$mT1uu45eeoYNuAQoDCxQTuc29uI3qi50RFGwn22/65duAA0Ycust2");
        nullUser.setPhoneNumber("+79131951099");
        userRepository.saveAndFlush(nullUser);
    }

    public List<PublicationPage> findAllPublicationPage() {
        return publicationRepository.findAll()
                .stream()
                .map(this::convertToPage)
                .toList();
    }

    public List<Publication> findAllPublication() {
        return publicationRepository.findAll();
    }



    public User findUserByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundException("Пользователь c логином " + login + " не найден"));
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь c id " + id + " не найден"));
    }

    public PublicationPage findPublicationPageById(Long id) {
        return convertToPage(Objects.requireNonNull(publicationRepository.findById(id).orElseThrow(() -> new PublicationNotFoundException("Лота с идентификатором " + id + " не существует"))));
    }

    public Publication findPublication(Long id) {
        return publicationRepository.findById(id).orElseThrow(() -> new PublicationNotFoundException("Лота с идентификатором " + id + " не существует"));
    }

    public void create(Publication publication, User holder) {
        if (Objects.isNull(publication.getUser())) {
            publication.setUser(nullUser);
        }
        publication.setPriceNow(1L);
        publication.setHolder(holder);
        publicationRepository.saveAndFlush(publication);
        convertToPage(publication);
        log.info("Пользователь {} опубликовал публикацию {}", holder.getLogin(), publication.getId());
    }

    public void update(Long id, Publication publication) {
            Publication publicationToBeUpdated = publicationRepository.findById(id).orElseThrow();
            publicationToBeUpdated.setCategory(publication.getCategory());
            publicationToBeUpdated.setCondition(publication.getCondition());
            publicationToBeUpdated.setDescriptionPublication(publication.getDescriptionPublication());
            publicationRepository.save(publicationToBeUpdated);
    }

    public void delete(Long id, User user) {
        Publication publication = publicationRepository.findById(id).orElseThrow(() -> new PublicationNotFoundException("Лота с идентификатором " + id + " не существует"));
        if(publication.getHolder().equals(user)) {
            log.info("Пользователь {} пытается удалить публикацию {}", user.getLogin(), publication.getId());
            publicationRepository.delete(publication);
            publicationRepository.flush();
        }
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
        log.info("Пользователь {} пытается сделать ставку {} на публикацию {}", user.getLogin(), newPrice, publication.getId());
        if (publication.getPriceNow() >= newPrice) {
            throw new NoValidUpPriceException("Новая ставка не может быть меньше, либо равна текущей");
        }
        if(publication.getHolder().equals(user)) {
            throw new NoValidUpPriceException("Владелец лота не может сделать ставку на свою публикацию");
        }
        publication.setPriceNow(newPrice);
        publication.setUser(user);
        publicationRepository.save(publication);
    }

    public void checkFinishTrade(Publication publication){
        if(publication.getDateOfFinishTrade().isBefore(LocalDateTime.now())) {
            publication.setStatusPublication(StatusPublication.SOLD);
            publicationRepository.save(publication);
            log.info("Публикация {} продана, победитель торгов пользователь {}", publication.getId(), publication.getUser().getLogin());
        }
        if(publication.getDateOfFinishTrade().plusDays(2).isBefore(LocalDateTime.now())) {
            publication.setStatusPublication(StatusPublication.ARCHIVE);
            publicationRepository.save(publication);
            log.info("Публикация {} переведена в архив", publication.getId());
        }
    }

}

