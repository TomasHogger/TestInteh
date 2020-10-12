package main.model.task.abstr;

import lombok.Getter;
import lombok.Setter;
import main.exception.RequirementStatusAndReadinessIncorrect;
import main.model.employee.abstr.Employee;
import main.model.state.TaskState;
import main.utils.CheckAndThrow;
import main.utils.TaskObjectCheck;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public abstract class Requirement extends TaskObject {
    private List<Task> tasks;

    public Requirement(LocalDate createDate, LocalDate startDate, LocalDate completedDate, TaskState taskState, Employee responsiblePerson, byte readiness, List<Task> tasks) {
        super(createDate, startDate, completedDate, taskState, responsiblePerson, readiness);

        CheckAndThrow.throwExceptionIfNotTrue(
                TaskObjectCheck.isNotAllTaskObjectCompletedOrTaskStateAndReadinessCorrect(tasks, readiness, taskState),
                new RequirementStatusAndReadinessIncorrect());

        this.tasks = tasks;
    }
}
