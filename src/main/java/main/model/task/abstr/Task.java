package main.model.task.abstr;

import lombok.Getter;
import lombok.Setter;
import main.model.employee.abstr.Employee;
import main.model.state.TaskState;

import java.time.LocalDate;

@Getter
@Setter
public abstract class Task extends TaskObject {
    private double coef;

    public Task(LocalDate createDate, LocalDate startDate, LocalDate completedDate, TaskState taskState, Employee responsiblePerson, byte readiness, double coef) {
        super(createDate, startDate, completedDate, taskState, responsiblePerson, readiness);
        this.coef = coef;
    }
}
