package ru.gb.PAGE;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PublicationPage {


    private String id;
    private String statusPublication;
    private String category;
    private String condition;
    private String startPrice;
    private String priceNow;
    private LocalDate datePublication;
    private LocalDate dateOfFinishTrade;
    private String descriptionPublication;
    private String client;
    private Long clientIdentity;
    private String holder;
    private Long holderIdentity;


}
