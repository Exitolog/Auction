package ru.gb.model;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewPrice {

    @Min(value = 1, message = "Новая ставка не может быть меньше 1 и меньше текущей ставки")
    private Long newPrice;

}
