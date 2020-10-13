package main.exception;

public class IllegalCompletedDateException extends RuntimeException {

    /**
     * Конструктор.
     */
    public IllegalCompletedDateException() {
        super("Completed date must equals max completed date in sub task or null");
    }
}
