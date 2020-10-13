package main.model.task.impl;

import main.model.employee.Employee;
import main.model.state.TaskState;
import main.model.task.abstr.Task;

import java.time.LocalDate;

public class BugTask extends Task {

    /**
     * Коэффициент для ПДЗ для данного типа задачи.
     */
    public static final double STANDARD_COEF = 0.8;

    /**
     * Конструктор.
     * @param createDate дата создания
     * @param startDate дата старта
     * @param completedDate дата окончания
     * @param taskState состояние задачи
     * @param responsiblePerson ответственное лицо
     * @param readiness готовность
     */
    public BugTask(LocalDate createDate, LocalDate startDate, LocalDate completedDate, TaskState taskState, Employee responsiblePerson, byte readiness) {
        super(createDate, startDate, completedDate, taskState, responsiblePerson, readiness, STANDARD_COEF);
    }
}
