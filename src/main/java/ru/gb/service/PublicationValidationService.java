package ru.gb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.entity.Publication;
import ru.gb.repository.PublicationRepository;

@Service
@RequiredArgsConstructor
public class PublicationValidationService {

    private final PublicationRepository publicationRepository;

    public String validNewPrice(Publication publication, Long newPrice){
        if(publication.getPriceNow() >= newPrice) return "Новая ставка не может быть меньше и равной предыдущей";
        return "";
    }
}
