package ru.gb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import ru.gb.service.PublicationPageService;

import java.time.LocalDateTime;
import java.util.Scanner;


/**
 * Hello world!
 *
 */
@SpringBootApplication
@Slf4j
public class ApplicationAuction {

    public static void main(String[] args) throws InterruptedException {

        ApplicationContext ctx = SpringApplication.run(ApplicationAuction.class, args);
        PublicationPageService publicationPageService = ctx.getBean(PublicationPageService.class);
        BeanApp beanApp = ctx.getBean(BeanApp.class);

        while (true) {
            try {
                Thread.sleep(1000 * 5);
                publicationPageService
                        .findAllPublication()
                        .forEach(publicationPageService::checkFinishTrade);
                log.info("Прошла валидация даты окончания торгов в {}", LocalDateTime.now());
                log.info("==================================");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
