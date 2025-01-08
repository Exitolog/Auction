package ru.gb.controller;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.service.PublicationService;
import ru.gb.entity.Publication;
import ru.gb.entity.User;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publications")
@Tag(name = "Публикации", description = "API для работы с публикациями")
@RequiredArgsConstructor
public class PublicationController {

    private final PublicationService publicationService;

    @GetMapping("{id}")
    public ResponseEntity<Publication> getById(@PathVariable("id") Long id){
        Optional<Publication> publication = publicationService.findById(id);
        if(publication.isPresent()) return ResponseEntity.status(HttpStatus.OK).body(publication.get());
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Publication>> getAll(){
        if(publicationService.findAll().isPresent()) {
            List<Publication> publications = publicationService.findAll().get();
            return ResponseEntity.ok(publications);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getAllPublicationByClient(@PathVariable("id") Long id){
            User userGet = publicationService.findUserById(id);
            return ResponseEntity.ok(userGet);
        //return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Publication> create(@RequestBody Publication publication){
        publication = publicationService.create(publication);
        return ResponseEntity.status(HttpStatus.CREATED).body(publication);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id){
        publicationService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
