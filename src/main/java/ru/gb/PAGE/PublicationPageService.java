package ru.gb.PAGE;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.REST.Publication;
import ru.gb.REST.PublicationRepository;
import ru.gb.REST.PublicationService;
import ru.gb.userLogic.User;
import ru.gb.userLogic.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
// вынести в пакет серисов
public class PublicationPageService {


    private final PublicationService publicationService;
    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;

    // убрать оптионал
    public Optional<List<PublicationPage>> findAll() {
        if (publicationService.findAll().isPresent()) {
            List<Publication> publications = publicationService.findAll().get();
            return Optional.of(publications.stream().map(this::convertToPage).toList());
        } else return Optional.empty();
    }

    public User findUserByLogin(String login){
        return publicationService.findUserByLogin(login);
    }

    public User findUserById(Long id) {
        return publicationService.findUserById(id);
    }

    // убрать оптионал везде
    public Optional<PublicationPage> findById(Long id) {
        Optional<Publication> publicationOptional = publicationService.findById(id);
        if (publicationOptional.isPresent()) {
            return Optional.of(convertToPage(publicationOptional.get()));
        } else return Optional.empty();
    }


    // сделать воид
    public PublicationPage create(Publication publication) {
        if (Objects.isNull(publication.getCategory())) {
            throw new IllegalArgumentException("Category must not be null");
        }
        if (Objects.isNull(publication.getCondition())) {
            throw new IllegalArgumentException("Condition must not be null");
        }
        if (Objects.isNull(publication.getUser())) {
            User user = new User();
            user.setLogin("admin");
            user.setPassword("admin_password");
            userRepository.save(user);
            publication.setUser(user);
        }
        if (Objects.isNull(publication.getHolder())) {
            User holder = new User();
            holder.setLogin("holder");
            holder.setPassword("holder_password");
            userRepository.save(holder);
            publication.setHolder(holder);
        }
        publicationRepository.save(publication);
        return convertToPage(publication);
    }

    public void update(Long id, Publication publication) {
        Publication publicationToBeUpdated = publicationRepository.findById(id).get();
        publicationToBeUpdated.setCategory(publication.getCategory());
        publicationToBeUpdated.setDateOfFinishTrade(publication.getDateOfFinishTrade());
        publicationToBeUpdated.setCondition(publication.getCondition());
        publicationToBeUpdated.setDescriptionPublication(publication.getDescriptionPublication());
        publicationRepository.save(publicationToBeUpdated);
        // сохранять в бд
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
        publicationPage.setDateOfFinishTrade(publication.getDateOfFinishTrade());
        publicationPage.setDescriptionPublication(publication.getDescriptionPublication());
        return publicationPage;
    }

}

