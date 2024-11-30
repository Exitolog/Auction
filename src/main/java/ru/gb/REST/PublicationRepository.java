package ru.gb.REST;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.userLogic.Client;

import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, Long> {

//    List<Publication> findAllByClientId(Long id);
//
//    Client findHolderById(Long id);
}
