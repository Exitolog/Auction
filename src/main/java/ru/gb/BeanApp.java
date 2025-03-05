package ru.gb;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.gb.entity.Publication;
import ru.gb.entity.Role;
import ru.gb.entity.User;
import ru.gb.entity.enums.Category;
import ru.gb.entity.enums.Condition;
import ru.gb.entity.enums.StatusPublication;
import ru.gb.repository.PublicationRepository;
import ru.gb.repository.RoleRepository;
import ru.gb.repository.UserRepository;


import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class BeanApp {

    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public BeanApp(PublicationRepository publicationRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.publicationRepository = publicationRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

        Publication myFirstLaptopPublication = new Publication();
        Publication myFirstPhonePublication = new Publication();
        Publication myFirstTelevisorPublication = new Publication();
        Publication mySecondTelevisorPublication = new Publication();
        Publication mySecondPhonePublication = new Publication();
        Publication mySecondLaptopPublication = new Publication();
        Publication myThirdLaptopPublication = new Publication();

        Role adminRole = new Role("ADMIN");
        roleRepository.save(adminRole);
        Role userRole = new Role("USER");
        roleRepository.save(userRole);

        User vanya = new User();
        vanya.setLogin("vanya_msk777");
        vanya.setPassword("$2a$12$TutxZPO5fH9I45Bo8wBNG.tlH2Uw7w4TslEaIN89PUyjIpLcHez3i");
        vanya.setConfirmPassword("$2a$12$TutxZPO5fH9I45Bo8wBNG.tlH2Uw7w4TslEaIN89PUyjIpLcHez3i");
//      vanya.setPassword("vanya_password");
        vanya.setRoles(List.of(adminRole, userRole));
        vanya.addPublication(myFirstPhonePublication);
        vanya.addPublication(mySecondTelevisorPublication);
        vanya.setPhoneNumber("+79131991099");
        userRepository.save(vanya);

        User tanya = new User();
        tanya.setLogin("tanya1998");
        tanya.setPassword("$2a$12$DpI.HhhhQp/OK4F2.bz91eoqENg3wJwwEsKYFahI3.FiLCaNy0S8y");
        tanya.setConfirmPassword("$2a$12$DpI.HhhhQp/OK4F2.bz91eoqENg3wJwwEsKYFahI3.FiLCaNy0S8y");
//      tanya.setPassword("tanya_password");
        tanya.setRoles(List.of(userRole));
        tanya.addPublication(myFirstPhonePublication);
        tanya.addPublication(mySecondPhonePublication);
        tanya.setPhoneNumber("+79992913454");
        userRepository.save(tanya);

        User pasha = new User();
        pasha.setLogin("pashaa222");
//      pasha.setPassword("pasha_password");
        pasha.setPassword("$2a$12$mIRZujcPyOFf39QE8zi9Ce6LjntRy6QO00gYUaHF6r2qFoJhQZnMS");
        pasha.setConfirmPassword("$2a$12$mIRZujcPyOFf39QE8zi9Ce6LjntRy6QO00gYUaHF6r2qFoJhQZnMS");
        pasha.setRoles(List.of(userRole));
        pasha.addPublication(myFirstLaptopPublication);
        pasha.addPublication(mySecondLaptopPublication);
        pasha.setPhoneNumber("+79262324999");
        userRepository.save(pasha);


        myFirstLaptopPublication.setCategory(Category.LAPTOP);
        myFirstLaptopPublication.setStatusPublication(StatusPublication.ACTIVE);
        myFirstLaptopPublication.setDateOfFinishTrade(LocalDateTime.now().plusSeconds(100L));
        myFirstLaptopPublication.setDescriptionPublication("Продаю ноутбук HP");
        myFirstLaptopPublication.setPriceNow(100L);
        myFirstLaptopPublication.setCondition(Condition.USED);
        myFirstLaptopPublication.setUser(vanya);
        myFirstLaptopPublication.setHolder(pasha);
        publicationRepository.save(myFirstLaptopPublication);

        myThirdLaptopPublication.setCategory(Category.LAPTOP);
        myThirdLaptopPublication.setStatusPublication(StatusPublication.SOLD);
        myThirdLaptopPublication.setDateOfFinishTrade(LocalDateTime.now().minusDays(1L));
        myThirdLaptopPublication.setDescriptionPublication("Продам б/у ноутбук ASUS ROG");
        myThirdLaptopPublication.setPriceNow(28000L);
        myThirdLaptopPublication.setCondition(Condition.USED);
        myThirdLaptopPublication.setUser(vanya);
        myThirdLaptopPublication.setHolder(tanya);
        publicationRepository.save(myThirdLaptopPublication);

        myFirstPhonePublication.setCategory(Category.PHONE);
        myFirstPhonePublication.setStatusPublication(StatusPublication.ACTIVE);
        myFirstPhonePublication.setDateOfFinishTrade(LocalDateTime.now().plusDays(2L));
        myFirstPhonePublication.setCondition(Condition.USED);
        myFirstPhonePublication.setPriceNow(780L);
        myFirstPhonePublication.setDescriptionPublication("Продаю телефон Samsung");
        myFirstPhonePublication.setUser(vanya);
        myFirstPhonePublication.setHolder(tanya);
        publicationRepository.save(myFirstPhonePublication);

        myFirstTelevisorPublication.setCategory(Category.TELEVISOR);
        myFirstTelevisorPublication.setStatusPublication(StatusPublication.ARCHIVE);
        myFirstTelevisorPublication.setDateOfFinishTrade(LocalDateTime.now().minusDays(3L));
        myFirstTelevisorPublication.setPriceNow(440L);
        myFirstTelevisorPublication.setUser(tanya);
        myFirstTelevisorPublication.setHolder(vanya);
        myFirstTelevisorPublication.setDescriptionPublication("Продаю телевизор LG (не работает экран)");
        myFirstTelevisorPublication.setCondition(Condition.INCORRECT);
        publicationRepository.save(myFirstTelevisorPublication);

        mySecondTelevisorPublication.setCategory(Category.TELEVISOR);
        mySecondTelevisorPublication.setStatusPublication(StatusPublication.ACTIVE);
        mySecondTelevisorPublication.setDateOfFinishTrade(LocalDateTime.now().plusMinutes(30L));
        mySecondTelevisorPublication.setDescriptionPublication("Продаю телевизор TCL");
        mySecondTelevisorPublication.setCondition(Condition.USED);
        mySecondTelevisorPublication.setUser(pasha);
        mySecondTelevisorPublication.setHolder(vanya);
        mySecondTelevisorPublication.setPriceNow(3000L);
        publicationRepository.save(mySecondTelevisorPublication);

        mySecondPhonePublication.setCategory(Category.PHONE);
        mySecondPhonePublication.setStatusPublication(StatusPublication.ACTIVE);
        mySecondPhonePublication.setDateOfFinishTrade(LocalDateTime.now().plusSeconds(40L).plusMinutes(1L));
        mySecondPhonePublication.setCondition(Condition.USED);
        mySecondPhonePublication.setPriceNow(12000L);
        mySecondPhonePublication.setUser(pasha);
        mySecondPhonePublication.setHolder(tanya);
        mySecondPhonePublication.setDescriptionPublication("Продаю телефон Iphone 6");
        publicationRepository.save(mySecondPhonePublication);

        mySecondLaptopPublication.setCategory(Category.LAPTOP);
        mySecondLaptopPublication.setStatusPublication(StatusPublication.SOLD);
        mySecondLaptopPublication.setDateOfFinishTrade(LocalDateTime.now().minusDays(1L).minusHours(2L).minusMinutes(44L));
        mySecondLaptopPublication.setDescriptionPublication("Продаю ноутбук MSI");
        mySecondLaptopPublication.setCondition(Condition.USED);
        mySecondLaptopPublication.setUser(tanya);
        mySecondLaptopPublication.setHolder(pasha);
        mySecondLaptopPublication.setPriceNow(2900L);
        publicationRepository.save(mySecondLaptopPublication);
    }
}
