package main.exception;

public class IllegalTaskArgumentsException extends RuntimeException {
    public IllegalTaskArgumentsException(String message) {
        super(message);
    }
}
