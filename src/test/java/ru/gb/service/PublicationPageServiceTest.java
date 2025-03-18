package ru.gb.service;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import ru.gb.SpringBootTestBase;
//import ru.gb.controller.PublicationPageController;
//import ru.gb.entity.Publication;
//import ru.gb.entity.User;
//import ru.gb.entity.enums.Category;
//import ru.gb.entity.enums.Condition;
//import ru.gb.repository.PublicationRepository;
//import ru.gb.repository.UserRepository;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//
//@WebMvcTest(PublicationPageController.class)
//class PublicationPageServiceTest {
//
//    @MockBean
//    private PublicationPageService publicationPageService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//
//
//    @Test
//    void testFindPublicationById() {
//        Publication publication = new Publication();
//        when(publicationPageService.findPublication(1L)).thenReturn(publication);
//    }
//
////    @Test
////    public void testFirstCreateNewPublication() {
////        Publication publication = new Publication();
////        when(publicationRepository.findById(publication.getId())).thenReturn(Optional.of(publication));
////    }
//
//
////    @Test
////    public void testSecondCreateNewPublication() {
////
////        Publication publication = new Publication();
////        publication.setCategory(Category.PHONE);
////        publication.setCondition(Condition.USED);
////        publication.setDateOfFinishTrade(LocalDateTime.now().plusDays(5L));
////
////        User user = new User();
////        user.setLogin("Ivan_22");
////        user.setPassword("ivan_password");
////        user.setConfirmPassword("ivan_password");
////        user.setPhoneNumber("+79231412413");
////        userRepository.saveAndFlush(user);
////        publicationPageService.create(publication, user);
////
////        Assertions.assertEquals(
////                publication.getCategory(),
////                publicationPageService.findPublication(publication.getId()).getCategory()
////        );
////    }
//
//
//    @Test
//    void testFindPublication() {
//
//    }
//}