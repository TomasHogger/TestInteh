package main.model.task.abstr;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import main.constants.ReadinessConstants;
import main.exception.DateTaskException;
import main.model.employee.abstr.Employee;
import main.model.state.TaskState;
import main.utils.CheckAndThrow;
import main.utils.DateCheckUtils;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public abstract class TaskObject {
    private LocalDate createDate;
    private LocalDate startDate;
    private LocalDate completedDate;
    private TaskState taskState;
    private Employee responsiblePerson;
    private byte readiness;

    public TaskObject(LocalDate createDate,
                      LocalDate startDate,
                      LocalDate completedDate,
                      TaskState taskState,
                      Employee responsiblePerson,
                      byte readiness) {

        CheckAndThrow.throwExceptionIfNotTrue(
                DateCheckUtils.isDatesInAscOrder(List.of(createDate, startDate, completedDate)),
                new DateTaskException(createDate, startDate, completedDate));

        DateCheckUtils.throwExceptionIfDatesIsNotInAscOrder(
                List.of(createDate, startDate, completedDate),
                new DateTaskException(createDate, startDate, completedDate));

        if (taskState != TaskState.NEW && startDate == null) {

        }

        if (taskState == TaskState.COMPLETED
                && (completedDate == null || readiness < ReadinessConstants.FULL_COMPLETED)) {
            //TODO
        }

        this.createDate = createDate;
        this.startDate = startDate;
        this.completedDate = completedDate;
        this.responsiblePerson = responsiblePerson;
        this.readiness = readiness;
    }
}
