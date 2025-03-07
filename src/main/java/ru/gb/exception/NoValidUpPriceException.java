package ru.gb.exception;

public class NoValidUpPriceException extends RuntimeException{
    public NoValidUpPriceException(String message) {
        super(message);
    }
}
