package ru.gb.REST;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.gb.model.Category;
import ru.gb.model.Condition;
import ru.gb.model.StatusPublication;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "publications")
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор публикации")
    private Long id;

    @Schema(description = "Состояние лота")
    private StatusPublication statusPublication = StatusPublication.ACTIVE;

    @Schema(description = "Категория лота")
    private Category category;

    @Schema(description = "Состояние лота")
    private Condition condition;

    @Schema(description = "Стартовая цена торгов")
    private Long startPrice = 1L;

    @Schema(description = "Дата публикации")
    private LocalDate datePublication = LocalDate.now();

    @Schema(description = "Дата окончания торгов")
    private LocalDate dateOfFinishTrade;

    @Schema(description = "Описание к публикации")
//    ,maxLength = 256)
    private String descriptionPublication;


}
