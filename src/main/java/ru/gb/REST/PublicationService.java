package ru.gb.REST;

import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.userLogic.Client;
import ru.gb.userLogic.ClientRepository;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublicationService {

    private final PublicationRepository publicationRepository;
    private final ClientRepository clientRepository;


    public Optional<Publication> findById(Long id){
        return publicationRepository.findById(id);
    }

//    public Optional<List<Publication>> findAllByClient(Long id){
//        return Optional.of(publicationRepository.findAllByClientId(id));
//    }

    public Optional<Client> findClientById(Long id){
        return clientRepository.findById(id);
    }


    public Optional<List<Publication>> findAll(){
        return Optional.of(publicationRepository.findAll());
    }

    public Publication create(Publication publication){
        if(Objects.isNull(publication.getCategory())){
            throw new IllegalArgumentException("Category must not be null");
        }
        if(Objects.isNull(publication.getCondition())){
            throw new IllegalArgumentException("Condition must not be null");
        }
        return publicationRepository.save(publication);
    }

    public void delete(Long id){
        publicationRepository.deleteById(id);
    }

}
