package ru.gb.entity.enums;

import lombok.Getter;
import ru.gb.entity.Another;
import ru.gb.entity.Laptop;
import ru.gb.entity.Phone;
import ru.gb.entity.Televisor;

import java.util.StringJoiner;

@Getter
public enum Category {

    LAPTOP("Ноутбук"), PHONE("Телефон"), TELEVISOR("Телевизор"), ANOTHER("Другое");

    private final String categoryName;

    Category(String categoryName) {
        this.categoryName = categoryName;
    }
}