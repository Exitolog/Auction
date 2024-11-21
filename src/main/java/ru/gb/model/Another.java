package ru.gb.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "anothers")
public class Another {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор другой категории")
    private Long id;

    @Schema(description = "Название другой категории")
    private String nameLaptop;

    @Schema(description = "Описание другой категории")
    private String descriptionLaptop;


}
