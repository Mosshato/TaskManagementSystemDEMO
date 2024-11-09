package org.example;

public class TooManyTasksException extends RuntimeException {

    public TooManyTasksException(String message) {
        super(message);
    }
}
