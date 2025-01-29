package ru.gb.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.entity.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long> {

}
