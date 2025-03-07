package ru.gb.exception;

public class PublicationNotFoundException extends RuntimeException{
    public PublicationNotFoundException(String message) {
        super(message);
    }
}
