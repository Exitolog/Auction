//package ru.gb.controller;
//
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import ru.gb.service.PublicationPageService;
//import ru.gb.service.PublicationValidationService;
//
//@RequiredArgsConstructor
//@RequestMapping("/auction/price")
//public class PriceController {
//
//    private final PublicationValidationService publicationValidationService;
//    private final PublicationPageService publicationPageService;
//
//
//
//    @PostMapping("/{id}")
//    public String updatePrice(@PathVariable("id") Long id, @ModelAttribute("newPrice") @Valid Long newPrice, BindingResult result, @AuthenticationPrincipal UserDetails currentUser) {
//        String newPriceErr = publicationValidationService
//                .validNewPrice(publicationPageService.findPublication(id), newPrice);
//        if (!newPriceErr.isEmpty()) {
//            ObjectError priceErr = new ObjectError("priceErr", newPriceErr);
//            result.addError(priceErr);
//        }
//        if (result.hasErrors()) {
//            System.out.println("New price sdsd" + newPrice);
//            System.out.println("Id sdsd " + id);
//            System.out.println("User 11 = " + currentUser.getUsername());
//            return "redirect:/auction/" + id;
//        }
//
//        publicationPageService.upPrice(publicationPageService.findPublication(id)
//                , newPrice
//                , publicationPageService.findUserByLogin(currentUser.getUsername()));
//        return "redirect:/auction/" + id;
//    }
//}
