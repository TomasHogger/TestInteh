package main.model.task.abstr;

import lombok.Getter;
import lombok.Setter;
import main.model.employee.Employee;
import main.model.state.TaskState;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public abstract class Task extends TaskObject {

    /**
     * Коэффициент для расчёта ПДЗ.
     */
    private double coef;

    /**
     * Конструктор.
     * @param createDate дата создания
     * @param startDate дата старта
     * @param completedDate дата окончания
     * @param taskState состояние задачи
     * @param responsiblePerson ответственное лицо
     * @param readiness готовность
     * @param coef коэффициент для расчёта ПДЗ
     */
    public Task(LocalDate createDate, LocalDate startDate, LocalDate completedDate, TaskState taskState, Employee responsiblePerson, byte readiness, double coef) {
        super(createDate, startDate, completedDate, taskState, responsiblePerson, readiness, null);
        this.coef = coef;
    }

    /**
     * Возвращаем null, т.к у данной задачи нет под.задач.
     * @return null
     */
    @Override
    public List<? extends TaskObject> getSubTask() {
        return null;
    }
}
