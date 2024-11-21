package ru.gb.PAGE;

import lombok.RequiredArgsConstructor;
import org.h2.engine.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.REST.Publication;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/auction")
@RequiredArgsConstructor
public class PublicationPageController {

    private final PublicationPageService publicationPageService;

    @GetMapping
    public String getAllPublicationPage(Model model) {
        Optional<List<PublicationPage>> publicationPages = publicationPageService.findAll();
        if (publicationPages.isPresent()) {
            model.addAttribute("publications", publicationPages.get());
            return "auction-page";
        } else return "not-found";
    }

    @GetMapping("/{id}")
    public String getPublicationById(@PathVariable("id") Long id, Model model) {
        Optional<PublicationPage> publicationPage = publicationPageService.findById(id);
        if (publicationPage.isEmpty()) {
            return "not-found";
        }
        model.addAttribute("publication", publicationPage.get());
        return "publication-page";
    }

    @GetMapping("/new")
    public String newPublication(Model model) {
        model.addAttribute("publication", new Publication());
        return "create-publication";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        if (publicationPageService.findById(id).isEmpty()) return "not-found";
        else {
            model.addAttribute("publication", publicationPageService.findById(id).get());
            return "edit";
        }
    }

    @PatchMapping("/{id}")
    public String updatePublication(@ModelAttribute("publication") Publication publication, @PathVariable("id") Long id) {
        publicationPageService.update(id, publication);
        return "redirect:/auction";
    }

    @DeleteMapping("/{id}")
    public String deletePublication(@PathVariable("id") Long id) {
        publicationPageService.delete(id);
        return "redirect:/auction";
    }

    @PostMapping("/new")
    public String createPublication(@ModelAttribute("publication") Publication publication) {
        publicationPageService.create(publication);
        return "redirect:/auction";
    }

}
