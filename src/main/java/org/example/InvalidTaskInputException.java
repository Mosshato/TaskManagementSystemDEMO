package org.example;

public class InvalidTaskInputException extends RuntimeException {

    public InvalidTaskInputException(String message) {
        super(message);
    }
}
