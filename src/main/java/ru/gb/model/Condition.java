package ru.gb.model;

public enum Condition {

    NEW("Новый"), USED("Б/У"), INCORRECT("Неисправно");

    private String conditionName;

    Condition(String conditionName) {
        this.conditionName = conditionName;
    }

    public String getConditionName() {
        return conditionName;
    }
}
