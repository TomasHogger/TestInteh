package main.model.task.impl;

import lombok.Getter;
import lombok.Setter;
import main.model.employee.Employee;
import main.model.state.TaskState;
import main.model.task.abstr.TaskObject;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Project extends TaskObject {

    /**
     * Список требований для проекта.
     */
    private List<? extends Requirement> requirements;

    /**
     * Конструктор.
     * @param createDate дата создания
     * @param startDate дата старта
     * @param completedDate дата окончания
     * @param taskState состояние задачи
     * @param responsiblePerson ответственное лицо
     * @param readiness готовность
     * @param requirements список требований для проекта
     */
    public Project(LocalDate createDate, LocalDate startDate, LocalDate completedDate, TaskState taskState, Employee responsiblePerson, byte readiness, List<Requirement> requirements) {
        super(createDate, startDate, completedDate, taskState, responsiblePerson, readiness, requirements);
        this.requirements = requirements;
    }

    /**
     * Возвращаем список требования для проекта.
     * @return requirements
     */
    @Override
    public List<? extends TaskObject> getSubTask() {
        return requirements;
    }
}
