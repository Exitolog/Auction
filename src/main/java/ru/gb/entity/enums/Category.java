package ru.gb.entity.enums;

import lombok.Getter;
import ru.gb.entity.Another;
import ru.gb.entity.Laptop;
import ru.gb.entity.Phone;
import ru.gb.entity.Televisor;

@Getter
public enum Category {

    LAPTOP(Laptop.class), PHONE(Phone.class), TELEVISOR(Televisor.class), ANOTHER(Another.class);

    private final Class category;

    Category(Class category) {
        this.category = category;
    }

}