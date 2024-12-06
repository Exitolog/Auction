package ru.gb;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gb.REST.Publication;
import ru.gb.REST.PublicationRepository;
import ru.gb.model.Category;
import ru.gb.model.Condition;
import ru.gb.model.StatusPublication;
import ru.gb.userLogic.Client;
import ru.gb.userLogic.ClientRepository;

import java.time.LocalDate;

@Component
public class BeanApp {

    private final PublicationRepository publicationRepository;
    private final ClientRepository userRepository;


    public BeanApp(PublicationRepository publicationRepository, ClientRepository userRepository) {
        this.publicationRepository = publicationRepository;
        this.userRepository = userRepository;
        Publication myFirstLaptopPublication = new Publication();
        Publication myFirstPhonePublication = new Publication();
        Publication myFirstTelevisorPublication = new Publication();
        Publication mySecondTelevisorPublication = new Publication();
        Publication mySecondPhonePublication = new Publication();
        Publication mySecondLaptopPublication = new Publication();

        Client vanya = new Client();
        vanya.setLogin("vanya_msk777");
        vanya.setPassword("vanya_password");
        vanya.addPublication(myFirstPhonePublication);
        vanya.addPublication(mySecondTelevisorPublication);
        userRepository.save(vanya);

        Client tanya = new Client();
        tanya.setLogin("tanya1998");
        tanya.setPassword("tanya_password");
        tanya.addPublication(myFirstPhonePublication);
        tanya.addPublication(mySecondPhonePublication);
        userRepository.save(tanya);

        Client pasha = new Client();
        pasha.setLogin("pashaa222");
        pasha.setPassword("pasha_password");
        pasha.addPublication(myFirstLaptopPublication);
        pasha.addPublication(mySecondLaptopPublication);
        userRepository.save(pasha);

        myFirstLaptopPublication.setCategory(Category.LAPTOP);
        myFirstLaptopPublication.setStatusPublication(StatusPublication.ACTIVE);
        myFirstLaptopPublication.setDateOfFinishTrade(LocalDate.of(2024, 11, 19));
        myFirstLaptopPublication.setDescriptionPublication("Продаю ноутбук HP");
        myFirstLaptopPublication.setPriceNow(100L);
        myFirstLaptopPublication.setCondition(Condition.USED);
        myFirstLaptopPublication.setClient(vanya);
        myFirstLaptopPublication.setHolder(pasha);
        publicationRepository.save(myFirstLaptopPublication);

        myFirstPhonePublication.setCategory(Category.PHONE);
        myFirstPhonePublication.setStatusPublication(StatusPublication.ACTIVE);
        myFirstPhonePublication.setDateOfFinishTrade(LocalDate.of(2024, 11, 20));
        myFirstPhonePublication.setCondition(Condition.USED);
        myFirstPhonePublication.setPriceNow(780L);
        myFirstPhonePublication.setDescriptionPublication("Продаю телефон Samsung");
        myFirstPhonePublication.setClient(vanya);
        myFirstPhonePublication.setHolder(tanya);
        publicationRepository.save(myFirstPhonePublication);

        myFirstTelevisorPublication.setCategory(Category.TELEVISOR);
        myFirstTelevisorPublication.setStatusPublication(StatusPublication.ARCHIVE);
        myFirstTelevisorPublication.setDateOfFinishTrade(LocalDate.of(2024,11,22));
        myFirstTelevisorPublication.setPriceNow(440L);
        myFirstTelevisorPublication.setClient(tanya);
        myFirstTelevisorPublication.setHolder(vanya);
        myFirstTelevisorPublication.setDescriptionPublication("Продаю телевизор LG (не работает экран)");
        myFirstTelevisorPublication.setCondition(Condition.INCORRECT);
        publicationRepository.save(myFirstTelevisorPublication);

        mySecondTelevisorPublication.setCategory(Category.TELEVISOR);
        mySecondTelevisorPublication.setStatusPublication(StatusPublication.ACTIVE);
        mySecondTelevisorPublication.setDateOfFinishTrade(LocalDate.of(2024,11,23));
        mySecondTelevisorPublication.setDescriptionPublication("Продаю телевизор TCL");
        mySecondTelevisorPublication.setCondition(Condition.USED);
        mySecondTelevisorPublication.setClient(pasha);
        mySecondTelevisorPublication.setHolder(vanya);
        mySecondTelevisorPublication.setPriceNow(3000L);
        publicationRepository.save(mySecondTelevisorPublication);

        mySecondPhonePublication.setCategory(Category.PHONE);
        mySecondPhonePublication.setStatusPublication(StatusPublication.ACTIVE);
        mySecondPhonePublication.setDateOfFinishTrade(LocalDate.of(2024, 11, 19));
        mySecondPhonePublication.setCondition(Condition.USED);
        mySecondPhonePublication.setPriceNow(12000L);
        mySecondPhonePublication.setClient(pasha);
        mySecondPhonePublication.setHolder(tanya);
        mySecondPhonePublication.setDescriptionPublication("Продаю телефон Iphone 6");
        publicationRepository.save(mySecondPhonePublication);

        mySecondLaptopPublication.setCategory(Category.LAPTOP);
        mySecondLaptopPublication.setStatusPublication(StatusPublication.SOLD);
        mySecondLaptopPublication.setDateOfFinishTrade(LocalDate.of(2024, 11, 20));
        mySecondLaptopPublication.setDescriptionPublication("Продаю ноутбук MSI");
        mySecondLaptopPublication.setCondition(Condition.USED);
        mySecondLaptopPublication.setClient(tanya);
        mySecondLaptopPublication.setHolder(pasha);
        mySecondLaptopPublication.setPriceNow(2900L);
        publicationRepository.save(mySecondLaptopPublication);

    }
}
