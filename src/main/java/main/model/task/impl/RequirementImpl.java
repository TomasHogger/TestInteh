package main.model.task.impl;

import main.model.employee.abstr.Employee;
import main.model.state.TaskState;
import main.model.task.abstr.Requirement;
import main.model.task.abstr.Task;

import java.time.LocalDate;
import java.util.List;

public class RequirementImpl extends Requirement {
    public RequirementImpl(LocalDate createDate, LocalDate startDate, LocalDate completedDate, TaskState taskState, Employee responsiblePerson, byte readiness, List<Task> tasks) {
        super(createDate, startDate, completedDate, taskState, responsiblePerson, readiness, tasks);
    }
}
