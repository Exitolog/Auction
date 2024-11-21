package ru.gb.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
@Entity
@Table(name = "laptops")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор ноутбука")
    private Long id;

    @Schema(description = "Название ноутбука")
    private String nameLaptop;

    @Schema(description = "Описание ноутбука")
    private String descriptionLaptop;


}
