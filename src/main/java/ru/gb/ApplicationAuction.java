package ru.gb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.gb.REST.Publication;
import ru.gb.REST.PublicationRepository;
import ru.gb.model.Category;
import ru.gb.model.Condition;
import ru.gb.model.StatusPublication;
import ru.gb.userLogic.Client;
import ru.gb.userLogic.ClientRepository;

import java.time.LocalDate;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class ApplicationAuction {



    public static void main( String[] args ) {

        ApplicationContext ctx = SpringApplication.run(ApplicationAuction.class, args);
        BeanApp beanApp = ctx.getBean(BeanApp.class);
    }
}
