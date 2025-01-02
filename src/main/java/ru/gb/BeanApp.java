package ru.gb;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.gb.REST.Publication;
import ru.gb.REST.PublicationRepository;
import ru.gb.model.Category;
import ru.gb.model.Condition;
import ru.gb.model.StatusPublication;
import ru.gb.userLogic.Role;
import ru.gb.userLogic.RoleRepository;
import ru.gb.userLogic.User;
import ru.gb.userLogic.UserRepository;

import java.time.LocalDate;
import java.util.List;
//
//@Component
//public class BeanApp {

//    private final PublicationRepository publicationRepository;
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//
//
//    // почитать про лики
//    public BeanApp(PublicationRepository publicationRepository, UserRepository userRepository, RoleRepository roleRepository) {
//        this.publicationRepository = publicationRepository;
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        Publication myFirstLaptopPublication = new Publication();
//        Publication myFirstPhonePublication = new Publication();
//        Publication myFirstTelevisorPublication = new Publication();
//        Publication mySecondTelevisorPublication = new Publication();
//        Publication mySecondPhonePublication = new Publication();
//        Publication mySecondLaptopPublication = new Publication();
//
//        Role adminRole = new Role("ADMIN");
//        roleRepository.save(adminRole);
//        Role userRole = new Role("USER");
//        roleRepository.save(userRole);
//
//        User vanya = new User();
//        vanya.setLogin("vanya_msk777");
//        vanya.setPassword("vanya_password");
//        vanya.setRoles(List.of(adminRole, userRole));
//        vanya.addPublication(myFirstPhonePublication);
//        vanya.addPublication(mySecondTelevisorPublication);
//        userRepository.save(vanya);
//
//        User tanya = new User();
//        tanya.setLogin("tanya1998");
//        tanya.setPassword("tanya_password");
//        tanya.setRoles(List.of(userRole));
//        tanya.addPublication(myFirstPhonePublication);
//        tanya.addPublication(mySecondPhonePublication);
//        userRepository.save(tanya);
//
//        User pasha = new User();
//        pasha.setLogin("pashaa222");
//        pasha.setPassword("pasha_password");
//        pasha.setRoles(List.of(userRole));
//        pasha.addPublication(myFirstLaptopPublication);
//        pasha.addPublication(mySecondLaptopPublication);
//        userRepository.save(pasha);
//
//
//        myFirstLaptopPublication.setCategory(Category.LAPTOP);
//        myFirstLaptopPublication.setStatusPublication(StatusPublication.ACTIVE);
//        myFirstLaptopPublication.setDateOfFinishTrade(LocalDate.of(2024, 11, 19));
//        myFirstLaptopPublication.setDescriptionPublication("Продаю ноутбук HP");
//        myFirstLaptopPublication.setPriceNow(100L);
//        myFirstLaptopPublication.setCondition(Condition.USED);
//        myFirstLaptopPublication.setUser(vanya);
//        myFirstLaptopPublication.setHolder(pasha);
//        publicationRepository.save(myFirstLaptopPublication);
//
//        myFirstPhonePublication.setCategory(Category.PHONE);
//        myFirstPhonePublication.setStatusPublication(StatusPublication.ACTIVE);
//        myFirstPhonePublication.setDateOfFinishTrade(LocalDate.of(2024, 11, 20));
//        myFirstPhonePublication.setCondition(Condition.USED);
//        myFirstPhonePublication.setPriceNow(780L);
//        myFirstPhonePublication.setDescriptionPublication("Продаю телефон Samsung");
//        myFirstPhonePublication.setUser(vanya);
//        myFirstPhonePublication.setHolder(tanya);
//        publicationRepository.save(myFirstPhonePublication);
//
//        myFirstTelevisorPublication.setCategory(Category.TELEVISOR);
//        myFirstTelevisorPublication.setStatusPublication(StatusPublication.ARCHIVE);
//        myFirstTelevisorPublication.setDateOfFinishTrade(LocalDate.of(2024,11,22));
//        myFirstTelevisorPublication.setPriceNow(440L);
//        myFirstTelevisorPublication.setUser(tanya);
//        myFirstTelevisorPublication.setHolder(vanya);
//        myFirstTelevisorPublication.setDescriptionPublication("Продаю телевизор LG (не работает экран)");
//        myFirstTelevisorPublication.setCondition(Condition.INCORRECT);
//        publicationRepository.save(myFirstTelevisorPublication);
//
//        mySecondTelevisorPublication.setCategory(Category.TELEVISOR);
//        mySecondTelevisorPublication.setStatusPublication(StatusPublication.ACTIVE);
//        mySecondTelevisorPublication.setDateOfFinishTrade(LocalDate.of(2024,11,23));
//        mySecondTelevisorPublication.setDescriptionPublication("Продаю телевизор TCL");
//        mySecondTelevisorPublication.setCondition(Condition.USED);
//        mySecondTelevisorPublication.setUser(pasha);
//        mySecondTelevisorPublication.setHolder(vanya);
//        mySecondTelevisorPublication.setPriceNow(3000L);
//        publicationRepository.save(mySecondTelevisorPublication);
//
//        mySecondPhonePublication.setCategory(Category.PHONE);
//        mySecondPhonePublication.setStatusPublication(StatusPublication.ACTIVE);
//        mySecondPhonePublication.setDateOfFinishTrade(LocalDate.of(2024, 11, 19));
//        mySecondPhonePublication.setCondition(Condition.USED);
//        mySecondPhonePublication.setPriceNow(12000L);
//        mySecondPhonePublication.setUser(pasha);
//        mySecondPhonePublication.setHolder(tanya);
//        mySecondPhonePublication.setDescriptionPublication("Продаю телефон Iphone 6");
//        publicationRepository.save(mySecondPhonePublication);
//
//        mySecondLaptopPublication.setCategory(Category.LAPTOP);
//        mySecondLaptopPublication.setStatusPublication(StatusPublication.SOLD);
//        mySecondLaptopPublication.setDateOfFinishTrade(LocalDate.of(2024, 11, 20));
//        mySecondLaptopPublication.setDescriptionPublication("Продаю ноутбук MSI");
//        mySecondLaptopPublication.setCondition(Condition.USED);
//        mySecondLaptopPublication.setUser(tanya);
//        mySecondLaptopPublication.setHolder(pasha);
//        mySecondLaptopPublication.setPriceNow(2900L);
//        publicationRepository.save(mySecondLaptopPublication);
//
//    }
//}
