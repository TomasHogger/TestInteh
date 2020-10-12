package main.utils;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DateCheckUtils {
    private DateCheckUtils(){ }
    public static boolean isDatesInAscOrder(final List<LocalDate> localDates) {
        List<LocalDate> localDatesWithoutNull = localDates
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (localDatesWithoutNull.size() <= 1) {
            return true;
        }

        Iterator<LocalDate> iter = localDatesWithoutNull.iterator();
        LocalDate current;
        LocalDate previous = iter.next();
        while (iter.hasNext()) {
            current = iter.next();
            if (previous.compareTo(current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    public static void throwExceptionIfDatesIsNotInAscOrder(final List<LocalDate> localDates, RuntimeException runtimeException) {
        if (!isDatesInAscOrder(localDates)) {
            throw runtimeException;
        }
    }
}
