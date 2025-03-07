package ru.gb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.gb.entity.enums.Category;
import ru.gb.entity.enums.Condition;
import ru.gb.entity.enums.StatusPublication;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

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
    private LocalDateTime datePublication = LocalDateTime.now();

    @Schema(description = "Дата окончания торгов")
    private LocalDateTime dateOfFinishTrade;

    @Schema(description = "User с лидирующей ставкой")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Schema(description = "User кому принадлежит лот")
    @ManyToOne
    @JoinColumn(name = "holder_id")
    private User holder;

    @Schema(description = "Описание к публикации")
    private String descriptionPublication;

//    @Override
//    public boolean equals(Object object) {
//        if (this == object) return true;
//        if (object == null || getClass() != object.getClass()) return false;
//
//        Publication that = (Publication) object;
//        return Objects.equals(id, that.id) && category == that.category && condition == that.condition && Objects.equals(datePublication, that.datePublication) && Objects.equals(holder, that.holder);
//    }

//    @Override
//    public int hashCode() {
//        int result = Objects.hashCode(category);
//        result = 31 * result + Objects.hashCode(condition);
//        result = 31 * result + Objects.hashCode(datePublication);
//        result = 31 * result + Objects.hashCode(holder);
//        return result;
//    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Publication.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("statusPublication=" + statusPublication)
                .add("category=" + category)
                .add("condition=" + condition)
                .add("user=" + user)
                .add("holder=" + holder)
                .add("descriptionPublication='" + descriptionPublication + "'")
                .toString();
    }
}
