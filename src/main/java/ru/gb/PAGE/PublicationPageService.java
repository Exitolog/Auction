package ru.gb.PAGE;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.REST.Publication;
import ru.gb.REST.PublicationRepository;
import ru.gb.REST.PublicationService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublicationPageService {


    private final PublicationService publicationService;
    private final PublicationRepository publicationRepository;

    public Optional<List<PublicationPage>> findAll() {
        if (publicationService.findAll().isPresent()) {
            List<Publication> publications = publicationService.findAll().get();
            return Optional.of(publications.stream().map(this::convertToPage).toList());
        } else return Optional.empty();
    }

    public Optional<PublicationPage> findById(Long id) {
        Optional<Publication> publicationOptional = publicationService.findById(id);
        if (publicationOptional.isPresent()) {
            return Optional.of(convertToPage(publicationOptional.get()));
        } else return Optional.empty();
    }

    public PublicationPage create(Publication publication) {
        if (Objects.isNull(publication.getCategory())) {
            throw new IllegalArgumentException("Category must not be null");
        }
        if (Objects.isNull(publication.getCondition())) {
            throw new IllegalArgumentException("Condition must not be null");
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
    }

    public void delete(Long id) {
        if (publicationRepository.findById(id).isPresent()) publicationRepository.deleteById(id);
        else throw new RuntimeException("Лота с идентификатором " + id + " не существует");
    }

    private PublicationPage convertToPage(Publication publication) {
        PublicationPage publicationPage = new PublicationPage();
        publicationPage.setCategory(publication.getCategory().name());
        publicationPage.setId(String.valueOf(publication.getId()));
        publicationPage.setDatePublication(String.valueOf(publication.getDatePublication()));
        publicationPage.setStatusPublication(publication.getStatusPublication().getStatusName());
        publicationPage.setCondition(String.valueOf(publication.getCondition()));
        publicationPage.setStartPrice(String.valueOf(publication.getStartPrice()));
        publicationPage.setDateOfFinishTrade(String.valueOf(publication.getDateOfFinishTrade()));
        publicationPage.setDescriptionPublication(publication.getDescriptionPublication());
        return publicationPage;
    }

}

