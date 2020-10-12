package main.utils;

public class CheckAndThrow {

    private CheckAndThrow() { }

    public static void throwExceptionIfNotTrue(boolean result, RuntimeException runtimeException) {
        if (!result) {
            throw runtimeException;
        }
    }
}
