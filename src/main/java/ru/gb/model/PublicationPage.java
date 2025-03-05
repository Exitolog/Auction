package ru.gb.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PublicationPage {


    private String id;
    private String statusPublication;
    private String category;
    private String condition;
    private String startPrice;
    private String priceNow;
    private String phoneHolder;
    private LocalDateTime datePublication;
    private LocalDateTime dateOfFinishTrade;
    private String descriptionPublication;
    private String user;
    private String loginUser;
    private String holder;
}
