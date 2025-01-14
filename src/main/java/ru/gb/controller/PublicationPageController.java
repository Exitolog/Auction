package ru.gb.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.gb.repository.UserRepository;
import ru.gb.service.PublicationPageService;
import ru.gb.entity.Publication;
import ru.gb.model.PublicationPage;
import ru.gb.entity.User;
import ru.gb.service.PublicationValidationService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/auction")
@RequiredArgsConstructor
public class PublicationPageController {

    private final PublicationPageService publicationPageService;
    private final UserRepository userRepository;
    private final PublicationValidationService publicationValidationService;

    @GetMapping
    public String getAllPublicationPage(Model model) {
        List<PublicationPage> publicationPages = publicationPageService.findAll();
        if (!publicationPages.isEmpty()) {
            model.addAttribute("publications", publicationPages);
            return "auction-page";
        } else return "not-found";
    }

    @GetMapping("/{id}")
    public String getPublicationById(@AuthenticationPrincipal UserDetails currentUser, @PathVariable("id") Long id, Model model) {
        PublicationPage publicationPage = publicationPageService.findPublicationPageById(id);
        User userHolder = userRepository.findByLogin(currentUser.getUsername()).orElse(null);
        if(publicationPage == null) return "not-found";
        if(publicationPageService.findPublication(id).getHolder().equals(userHolder)) {
            model.addAttribute("publication", publicationPage);
            model.addAttribute("username", userHolder.getLogin());
            return "publication-page-holder";
        }
        model.addAttribute("publication", publicationPage);
        model.addAttribute("username", userHolder.getLogin());
        return "publication-page";
    }

    @GetMapping("/new")
    public String newPublication(Model model) {
        model.addAttribute("publication", new Publication());
        return "create-publication";
    }

    @GetMapping("/user/{login}")
    public String getAllPublicationByClient(Model model, @PathVariable("login") String login){
            User userGet = publicationPageService.findUserByLogin(login);
            model.addAttribute("client", userGet);
            return "client-page";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
            model.addAttribute("publication", publicationPageService.findPublicationPageById(id));
            return "edit";
    }

    @PatchMapping("/{id}")
    public String updatePublication(@ModelAttribute("publication") Publication publication, @PathVariable("id") Long id) {
        if(publication == null) return "redirect:/auction";
        publicationPageService.update(id, publication);
        return "redirect:/auction";
    }

    @DeleteMapping("/{id}")
    public String deletePublication(@PathVariable("id") Long id) {
        publicationPageService.delete(id);
        return "redirect:/auction";
    }

    @PostMapping("/new")
    public String createPublication(@AuthenticationPrincipal UserDetails currentUser, @ModelAttribute("publication") Publication publication) {
        User userHolder = userRepository.findByLogin(currentUser.getUsername()).get();
        publication.setHolder(userHolder);
        publicationPageService.create(publication);
        return "redirect:/auction";
    }


    @PostMapping("/{id}")
    public String updatePrice(@PathVariable("id") Long id, @ModelAttribute("newPrice") @Valid Long newPrice, BindingResult result, @AuthenticationPrincipal UserDetails currentUser){
        String newPriceErr = publicationValidationService
               .validNewPrice(publicationPageService.findPublication(id), newPrice);
        if(!newPriceErr.isEmpty()) {
            ObjectError priceErr = new ObjectError("priceErr", newPriceErr);
            result.addError(priceErr);
        }
        if(result.hasErrors()){
            return "redirect:/auction/" + id;
        }
        publicationPageService.upPrice(publicationPageService.findPublication(id)
                ,newPrice
                ,publicationPageService.findUserByLogin(currentUser.getUsername()));
        return "redirect:/auction/" + id;
    }



}
