package ru.gb.REST;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.gb.model.Category;
import ru.gb.model.Condition;
import ru.gb.model.StatusPublication;
import ru.gb.userLogic.Client;


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
    @JoinColumn(name = "client_id")
    private Client client;

    @Schema(description = "ID user с лидирующей ставкой")
    private Long clientIdentity;

    @Schema(description = "Логин user кому принадлежит лот")
    @ManyToOne
    @JoinColumn(name = "holder_id")
    private Client holder;

    @Schema(description = "ID user кому принадлежит лот")
    private Long holderIdentity;

    @Schema(description = "Описание к публикации")
    private String descriptionPublication;

    public Publication() {
    }

    public Publication(Client client, Client holder) {
        this.client = client;
        this.holder = holder;
        this.clientIdentity = client.getId();
        this.holderIdentity = holder.getId();
    }
}
