package ru.gb.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gb.SpringBootTestBase;
import ru.gb.entity.Publication;
import ru.gb.entity.User;
import ru.gb.entity.enums.Category;
import ru.gb.entity.enums.Condition;
import ru.gb.repository.UserRepository;

import java.time.LocalDateTime;

class PublicationPageServiceTest extends SpringBootTestBase {

    @Autowired
    private PublicationPageService publicationPageService;
    @Autowired
    private UserRepository userRepository;


    @Test
    void findAllPublicationPage() {
        System.out.println("ffff");
    }

    @Test
    public void testFirstCreateNewPublication() {
        Publication publication = new Publication();
        publication.setCategory(Category.LAPTOP);
        publication.setCondition(Condition.NEW);
        publication.setDateOfFinishTrade(LocalDateTime.now().plusDays(10L));
        User user = new User();
        user.setLogin("Sergey");
        user.setPassword("sergey_password");
        user.setConfirmPassword("sergey_password");
        user.setPhoneNumber("+79131951099");
        userRepository.saveAndFlush(user);
        publicationPageService.create(publication, user);

        Assertions.assertEquals(
                publication.getId(),
                publicationPageService.findPublication(publication.getId()).getId()
        );
    }


    @Test
    public void testSecondCreateNewPublication() {

        Publication publication = new Publication();
        publication.setCategory(Category.PHONE);
        publication.setCondition(Condition.USED);
        publication.setDateOfFinishTrade(LocalDateTime.now().plusDays(5L));

        User user = new User();
        user.setLogin("Ivan_22");
        user.setPassword("ivan_password");
        user.setConfirmPassword("ivan_password");
        user.setPhoneNumber("+79231412413");
        userRepository.saveAndFlush(user);
        publicationPageService.create(publication, user);

        Assertions.assertEquals(
                publication.getCategory(),
                publicationPageService.findPublication(publication.getId()).getCategory()
        );

    }


}