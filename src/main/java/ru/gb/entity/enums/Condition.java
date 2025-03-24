package ru.gb.entity.enums;

import lombok.Getter;

@Getter
public enum Condition {

    NEW("Новый"), USED("Б/У"), INCORRECT("Неисправно");

    private String conditionName;

    Condition(String conditionName) {
        this.conditionName = conditionName;
    }

}
