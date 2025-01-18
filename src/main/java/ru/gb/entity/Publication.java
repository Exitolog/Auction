package ru.gb.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.gb.entity.enums.Category;
import ru.gb.entity.enums.Condition;
import ru.gb.entity.enums.LifeTimePublication;
import ru.gb.entity.enums.StatusPublication;


import java.time.LocalDate;

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

    @Schema(description = "Текущая цена торгов")
    private Long priceNow;

    @Schema(description = "Дата публикации")
    private LocalDate datePublication = LocalDate.now();

    @Schema(description = "Дата окончания торгов")
    private LocalDate dateOfFinishTrade;

    @Schema(description = "Логин user с лидирующей ставкой")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Schema(description = "Логин user кому принадлежит лот")
    @ManyToOne
    @JoinColumn(name = "holder_id")
    private User holder;

    @Schema(description = "Описание к публикации")
    private String descriptionPublication;

    public Publication() {
    }

    public Publication(User user, User holder) {
        this.user = user;
        this.holder = holder;
    }


}
