package ru.gb.model;

import lombok.Getter;

@Getter
public enum Category {

    LAPTOP(Laptop.class), PHONE(Phone.class), TELEVISOR(Televisor.class), ANOTHER(Another.class);

    private final Class category;

    Category(Class category) {
        this.category = category;
    }

}