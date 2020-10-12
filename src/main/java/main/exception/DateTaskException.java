package main.exception;

import java.time.LocalDate;

public class DateTaskException extends RuntimeException {
    public DateTaskException(LocalDate createDate, LocalDate startDate, LocalDate completedDate) {
        super("Not available date. Create date %s, start date %s, %s completed date".formatted(createDate, startDate, completedDate));
    }
}
