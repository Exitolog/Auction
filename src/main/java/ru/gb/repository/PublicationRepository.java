package ru.gb.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.entity.Publication;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {

}
