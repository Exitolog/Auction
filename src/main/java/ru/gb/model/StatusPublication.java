package ru.gb.model;

import lombok.Getter;

@Getter
public enum StatusPublication {

    SOLD("Продано"), ARCHIVE("В архиве"), ACTIVE("Активные торги");

    private final String statusName;

    StatusPublication(String statusName) {
        this.statusName = statusName;
    }

}
