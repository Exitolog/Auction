package ru.gb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.gb.PAGE.PublicationPage;
import ru.gb.REST.Publication;
import ru.gb.REST.PublicationRepository;
import ru.gb.model.Category;
import ru.gb.model.Condition;
import ru.gb.model.StatusPublication;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class ApplicationAuction {



    public static void main( String[] args ) {

        ApplicationContext ctx = SpringApplication.run(ApplicationAuction.class, args);
        PublicationRepository publicationRepository = ctx.getBean(PublicationRepository.class);


        Publication myFirstLaptopPublication = new Publication();
        myFirstLaptopPublication.setCategory(Category.LAPTOP);
        myFirstLaptopPublication.setStatusPublication(StatusPublication.ACTIVE);
        myFirstLaptopPublication.setDateOfFinishTrade(LocalDate.of(2024, 11, 19));
        //myFirstLaptopPublication.setDateOfFinishTrade(LocalDateTime.of(2024, 11, 19, 10, 6));
        myFirstLaptopPublication.setDescriptionPublication("Продаю ноутбук HP");
        myFirstLaptopPublication.setCondition(Condition.USED);
        publicationRepository.save(myFirstLaptopPublication);

        Publication myFirstPhonePublication = new Publication();
        myFirstPhonePublication.setCategory(Category.PHONE);
        myFirstPhonePublication.setStatusPublication(StatusPublication.ACTIVE);
        myFirstPhonePublication.setDateOfFinishTrade(LocalDate.of(2024, 11, 20));
        //myFirstPhonePublication.setDateOfFinishTrade(LocalDateTime.of(2024, 11, 20, 11, 57));
        myFirstPhonePublication.setCondition(Condition.USED);
        myFirstPhonePublication.setDescriptionPublication("Продаю телефон Samsung");
        publicationRepository.save(myFirstPhonePublication);

        Publication myFirstTelevisorPublication = new Publication();
        myFirstTelevisorPublication.setCategory(Category.TELEVISOR);
        myFirstTelevisorPublication.setStatusPublication(StatusPublication.ARCHIVE);
        myFirstTelevisorPublication.setDateOfFinishTrade(LocalDate.of(2024,11,22));
        //myFirstTelevisorPublication.setDateOfFinishTrade(LocalDateTime.of(2024,11,22, 14, 12));
        myFirstTelevisorPublication.setDescriptionPublication("Продаю телевизор LG (не работает экран)");
        myFirstTelevisorPublication.setCondition(Condition.INCORRECT);
        publicationRepository.save(myFirstTelevisorPublication);

        Publication mySecondTelevisorPublication = new Publication();
        mySecondTelevisorPublication.setCategory(Category.TELEVISOR);
        mySecondTelevisorPublication.setStatusPublication(StatusPublication.ACTIVE);
        mySecondTelevisorPublication.setDateOfFinishTrade(LocalDate.of(2024,11,23));
        //mySecondTelevisorPublication.setDateOfFinishTrade(LocalDateTime.of(2024,11,23, 10, 26));
        mySecondTelevisorPublication.setDescriptionPublication("Продаю телевизор TCL");
        mySecondTelevisorPublication.setCondition(Condition.USED);
        publicationRepository.save(mySecondTelevisorPublication);

        Publication mySecondPhonePublication = new Publication();
        mySecondPhonePublication.setCategory(Category.PHONE);
        mySecondPhonePublication.setStatusPublication(StatusPublication.ACTIVE);
        mySecondPhonePublication.setDateOfFinishTrade(LocalDate.of(2024, 11, 19));
        //mySecondPhonePublication.setDateOfFinishTrade(LocalDateTime.of(2024, 11, 19, 21, 11));
        mySecondPhonePublication.setCondition(Condition.USED);
        mySecondPhonePublication.setDescriptionPublication("Продаю телефон Iphone 6");
        publicationRepository.save(mySecondPhonePublication);

        Publication mySecondLaptopPublication = new Publication();
        mySecondLaptopPublication.setCategory(Category.LAPTOP);
        mySecondLaptopPublication.setStatusPublication(StatusPublication.SOLD);
        mySecondLaptopPublication.setDateOfFinishTrade(LocalDate.of(2024, 11, 20));
        //mySecondLaptopPublication.setDateOfFinishTrade(LocalDateTime.of(2024, 11, 20, 3, 54));
        mySecondLaptopPublication.setDescriptionPublication("Продаю ноутбук MSI");
        mySecondLaptopPublication.setCondition(Condition.USED);
        publicationRepository.save(mySecondLaptopPublication);

        PublicationPage publicationPage = new PublicationPage();

    }



}
