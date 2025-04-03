package ru.gb.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.gb.service.FileUploadService;
import ru.gb.service.PublicationPageService;
import ru.gb.entity.Publication;
import ru.gb.model.PublicationPage;
import ru.gb.entity.User;
import ru.gb.service.PublicationValidationService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequestMapping("/auction")
@RequiredArgsConstructor
public class PublicationPageController {

    private final PublicationPageService publicationPageService;
    private final PublicationValidationService publicationValidationService;

    //Базовый запрос на главную страница аукциона
    @GetMapping
    public String getAllPublicationPage(Model model) {
        List<PublicationPage> publicationPages = publicationPageService.findAllPublicationPage();
        if (!publicationPages.isEmpty()) {
            model.addAttribute("publications", publicationPages);
            return "auction-page";
        } else return "not-found";
    }

    //Гет запрос к публикации по идентификатору
    @GetMapping("/{id}")
    public String getPublicationById(@AuthenticationPrincipal UserDetails currentUser, @PathVariable("id") Long id, Model model) {
        PublicationPage publicationPage = publicationPageService.findPublicationPageById(id);
        User userHolder = publicationPageService.findUserByLogin(currentUser.getUsername());
        if (publicationPage == null) return "not-found";
        model.addAttribute("publication", publicationPage);
        model.addAttribute("username", userHolder.getLogin());
        model.addAttribute("userWin", userHolder);
        return "publication-page";
    }

    //Гет запрос к странице с созданием новой публикации
    @GetMapping("/new")
    public String newPublication(Model model) {
        model.addAttribute("publication", new Publication());
        model.addAttribute("threeDays", LocalDateTime.now().plusDays(3));
        model.addAttribute("fiveDays", LocalDateTime.now().plusDays(5));
        model.addAttribute("sevenDays", LocalDateTime.now().plusDays(7));
        return "create-publication";
    }

    //FIXME: некорректно отображает список публикаций
    @GetMapping("/user/{login}")
    public String getAllPublicationByClient(Model model, @PathVariable("login") String login) {
        model.addAttribute("client", publicationPageService.findUserByLogin(login));
        return "client-page";
    }

    //Гет запрос к странице для изменения публикации
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("publication", publicationPageService.findPublicationPageById(id));
        return "edit";
    }


    //Пост запрос на изменение информации по лоту
    @PostMapping("/edit/{id}")
    public String updatePublication(@AuthenticationPrincipal UserDetails currentUser,
                                    @ModelAttribute Publication publication,
                                    @PathVariable("id") Long id,
                                    RedirectAttributes redirectAttributes) {
        if(publicationPageService.findPublication(id).getHolder().equals(publicationPageService.findUserByLogin(currentUser.getUsername()))) {
            publicationPageService.update(id, publication);
            redirectAttributes.addFlashAttribute("message", "Ваш лот " + id + " успешно обновлен!");
        }
        return "redirect:/auction";
    }


    //Запрос на удаление publication от владельца(с проверкой)
    @PostMapping("/delete")
    public String deletePublication(@AuthenticationPrincipal UserDetails currentUser,
                                    @RequestParam("id") Long id,
                                    RedirectAttributes redirectAttributes) {
        publicationPageService.delete(id, publicationPageService.findUserByLogin(currentUser.getUsername()));
        redirectAttributes.addFlashAttribute("message", "Ваш лот " + id + " успешно удален!");
        return "redirect:/auction";
    }

    //Запрос на создание публикации
    @PostMapping("/new")
    public String createPublication(@AuthenticationPrincipal UserDetails currentUser,
                                    @ModelAttribute("publication") Publication publication,
                                    RedirectAttributes redirectAttributes,
                                    BindingResult result,
                                    @RequestParam("image") MultipartFile multipartFile) throws IOException {

        if(!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            publication.setImages(fileName);

            publicationPageService.create(publication, publicationPageService.findUserByLogin(currentUser.getUsername()));
            String uploadDir = "publication-images/"+currentUser.getUsername()+"/"+publication.getId();
            FileUploadService.uploadFile(uploadDir, fileName, multipartFile);

        } else publicationPageService.create(publication, publicationPageService.findUserByLogin(currentUser.getUsername()));
        redirectAttributes.addFlashAttribute("message", "Ваш лот  " + publication.getId() + " успешно опубликован!");
        return "redirect:/auction";
    }

    //Запрос на обновление цены лота для пользователей(с проверкой)
    @PostMapping("/{id}")
    public String updatePrice(@PathVariable("id") Long id,
                              @ModelAttribute("newPrice") @Valid Long newPrice,
                              BindingResult result,
                              @AuthenticationPrincipal UserDetails currentUser) {

        String newPriceErr = publicationValidationService
                .validNewPrice(publicationPageService.findPublication(id), newPrice);
        if (!newPriceErr.isEmpty()) {
            ObjectError priceErr = new ObjectError("priceErr", newPriceErr);
            result.addError(priceErr);
        }
        if (result.hasErrors()) {
            return "redirect:/auction/" + id;
        }
        publicationPageService.upPrice(publicationPageService.findPublication(id)
                , newPrice
                , publicationPageService.findUserByLogin(currentUser.getUsername()));
        return "redirect:/auction/" + id;
    }
}
