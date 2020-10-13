package main.exception;

public class ReadinessValueException extends RuntimeException {
    /**
     * Конструктор.
     * @param value готовность задачи
     */
    public ReadinessValueException(byte value) {
        super("Value is %d. Available from 1 to 100".formatted(value));
    }
}
