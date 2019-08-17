package ru.gasis.exceptions;

public class GasIsUserNotFoundException extends Exception {

    public GasIsUserNotFoundException(String message) {
        super(message);
    }

    public GasIsUserNotFoundException() {
        super("User don't exist!");
    }
}
