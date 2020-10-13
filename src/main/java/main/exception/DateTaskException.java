package main.exception;

import java.time.LocalDate;

public class DateTaskException extends RuntimeException {

    /**
     * Конструктор.
     * @param createDate дата создания задачи
     * @param startDate дата старта задачи
     * @param completedDate дата окончания задачи
     */
    public DateTaskException(LocalDate createDate, LocalDate startDate, LocalDate completedDate) {
        super("Not available date. Create date %s, start date %s, completed date %s".formatted(createDate, startDate, completedDate));
    }
}
