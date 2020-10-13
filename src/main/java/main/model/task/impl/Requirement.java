package main.model.task.impl;

import lombok.Getter;
import lombok.Setter;
import main.model.employee.Employee;
import main.model.state.TaskState;
import main.model.task.abstr.Task;
import main.model.task.abstr.TaskObject;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Requirement extends TaskObject {

    /**
     * Список задач для требования.
     */
    private List<? extends Task> tasks;

    /**
     * Конструктор.
     * @param createDate дата создания
     * @param startDate дата старта
     * @param completedDate дата окончания
     * @param taskState состояние задачи
     * @param responsiblePerson ответственное лицо
     * @param readiness готовность
     * @param tasks список задач для требования
     */
    public Requirement(LocalDate createDate, LocalDate startDate, LocalDate completedDate, TaskState taskState, Employee responsiblePerson, byte readiness, List<Task> tasks) {
        super(createDate, startDate, completedDate, taskState, responsiblePerson, readiness, tasks);
        this.tasks = tasks;
    }

    /**
     * Возвращаем список задач для требования.
     * @return tasks
     */
    @Override
    public List<? extends TaskObject> getSubTask() {
        return tasks;
    }
}
