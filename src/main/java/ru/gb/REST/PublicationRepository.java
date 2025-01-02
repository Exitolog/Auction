package ru.gb.REST;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<Publication, Long> {

//    List<Publication> findAllByClientId(Long id);
//
//    Client findHolderById(Long id);
}
