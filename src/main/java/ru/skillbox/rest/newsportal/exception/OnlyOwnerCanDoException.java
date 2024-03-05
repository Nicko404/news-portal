package ru.skillbox.rest.newsportal.exception;

public class OnlyOwnerCanDoException extends RuntimeException {

    public OnlyOwnerCanDoException(String message) {
        super(message);
    }
}
