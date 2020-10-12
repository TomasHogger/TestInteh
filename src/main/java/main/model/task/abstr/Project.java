package main.model.task.abstr;

import lombok.Getter;
import lombok.Setter;
import main.exception.ProjectStatusAndReadinessIncorrect;
import main.exception.RequirementStatusAndReadinessIncorrect;
import main.model.employee.abstr.Employee;
import main.model.state.TaskState;
import main.utils.CheckAndThrow;
import main.utils.TaskObjectCheck;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public abstract class Project extends TaskObject {
    private List<Requirement> requirements;

    public Project(LocalDate createDate, LocalDate startDate, LocalDate completedDate, TaskState taskState, Employee responsiblePerson, byte readiness, List<Requirement> requirements) {
        super(createDate, startDate, completedDate, taskState, responsiblePerson, readiness);

        CheckAndThrow.throwExceptionIfNotTrue(
                TaskObjectCheck.isNotAllTaskObjectCompletedOrTaskStateAndReadinessCorrect(requirements, readiness, taskState),
                new ProjectStatusAndReadinessIncorrect());

        this.requirements = requirements;
    }
}
