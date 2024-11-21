package ru.gb.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "phones")
public class Phone{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор телефона")
    private Long id;

    @Schema(description = "Название телефона")
    private String namePhone;

    @Schema(description = "Описание телефона")
    private String descriptionPhone;


}
