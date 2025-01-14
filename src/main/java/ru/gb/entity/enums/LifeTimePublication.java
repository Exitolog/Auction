package ru.gb.entity.enums;

import java.time.LocalDate;

public enum LifeTimePublication {

    THREE(LocalDate.now().plusDays(3)),
    SEVEN(LocalDate.now().plusDays(7)),
    FIVE(LocalDate.now().plusDays(5));

    private final LocalDate dateOfFinish;

    LifeTimePublication(LocalDate dateOfFinish) {
        this.dateOfFinish = dateOfFinish;
    }

    public LocalDate getDateOfFinish() {
        return dateOfFinish;
    }
}
