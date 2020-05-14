package com.thoughtworks.guessnumber;

public class GameAnswerException extends RuntimeException {
    public GameAnswerException() {
        super();
    }

    public GameAnswerException(String message) {
        super(message);
    }
}
