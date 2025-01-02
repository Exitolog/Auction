package ru.gb.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "televisors")
public class Televisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор телевизора")
    private Long id;

    @Schema(description = "Название телевизора")
    private String nameTelevisor;

    @Schema(description = "Описание телевизора")
    private String descriptionTelevisor;


}
