package main.exception;

public class StatusAndReadinessIncorrect extends RuntimeException {
    /**
     * Конструктор.
     * @param message сообщение.
     */
    public StatusAndReadinessIncorrect(String message) {
        super(message);
    }
}
