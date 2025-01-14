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
        Publication publication = publicationService.findById(id);
        if(publication != null) return ResponseEntity.status(HttpStatus.OK).body(publication);
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
