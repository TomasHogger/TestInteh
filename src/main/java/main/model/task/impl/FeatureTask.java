package main.model.task.impl;

import main.model.employee.abstr.Employee;
import main.model.state.TaskState;
import main.model.task.abstr.Task;

import java.time.LocalDate;

public class FeatureTask extends Task {

    public static final double STANDARD_COEF = 0.9;

    public FeatureTask(LocalDate createDate, LocalDate startDate, LocalDate completedDate, TaskState taskState, Employee responsiblePerson, byte readiness) {
        super(createDate, startDate, completedDate, taskState, responsiblePerson, readiness, STANDARD_COEF);
    }
}
