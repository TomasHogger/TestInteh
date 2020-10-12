package main.model.task.impl;

import main.model.employee.abstr.Employee;
import main.model.state.TaskState;
import main.model.task.abstr.Project;
import main.model.task.abstr.Requirement;

import java.time.LocalDate;
import java.util.List;

public class ProjectImpl extends Project {

    public ProjectImpl(LocalDate createDate, LocalDate startDate, LocalDate completedDate, TaskState taskState, Employee responsiblePerson, byte readiness, List<Requirement> requirements) {
        super(createDate, startDate, completedDate, taskState, responsiblePerson, readiness, requirements);
    }
}
