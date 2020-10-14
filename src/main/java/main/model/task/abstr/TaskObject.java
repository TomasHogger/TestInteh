package main.model.task.abstr;

import lombok.Getter;
import lombok.Setter;
import main.exception.*;
import main.model.employee.Employee;
import main.model.state.TaskState;
import main.utils.DateCheckUtils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public abstract class TaskObject {
    /**
     * Максимальное значение поля readiness.
     */
    public static final byte FULL_COMPLETED = 100;
    /**
     * Минимальное значение поля readiness.
     */
    public static final byte START = 1;
    /**
     * Дата создания задачи.
     */
    private LocalDate createDate;
    /**
     * Дата старта задачи.
     */
    private LocalDate startDate;
    /**
     * Дата окончания задачи.
     */
    private LocalDate completedDate;
    /**
     * Состояние задачи.
     */
    private TaskState taskState;
    /**
     * Ответственное лицо.
     */
    private Employee responsiblePerson;
    /**
     * Готовность.
     */
    private byte readiness;

    /**
     * Конструктор.
     * @param createDate дата создания
     * @param startDate дата старта
     * @param completedDate дата окончания
     * @param taskState состояние задачи
     * @param responsiblePerson ответственное лицо
     * @param readiness готовность
     * @param subTask под.задачи. Если null, то под.задач нет
     */
    public TaskObject(LocalDate createDate,
                      LocalDate startDate,
                      LocalDate completedDate,
                      TaskState taskState,
                      Employee responsiblePerson,
                      byte readiness,
                      List<? extends TaskObject> subTask) {

        if (!DateCheckUtils.isDatesInAscOrder(Arrays.asList(createDate, startDate, completedDate))) {
            throw new IllegalTaskArgumentsException(
                    "Not available date. Create date %s, start date %s, completed date %s".formatted(
                            createDate,
                            startDate,
                            completedDate));
        }

        if (taskState != TaskState.NEW && startDate == null) {
            throw new IllegalTaskArgumentsException("You need to set startDate with task state NEW");
        }

        if (taskState == TaskState.COMPLETED
                && (completedDate == null || readiness < FULL_COMPLETED)) {
            throw new IllegalTaskArgumentsException(
                    "You need to set completedDate and readiness to 100 with task state COMPLETED");
        }

        if (readiness < START || readiness > FULL_COMPLETED) {
            throw new IllegalTaskArgumentsException("Value is %d. Available from 1 to 100".formatted(readiness));
        }

        if (subTask != null
                && subTask.stream().allMatch(task -> task.getTaskState() == TaskState.COMPLETED)
                && (taskState != TaskState.COMPLETED || readiness != FULL_COMPLETED)) {
            throw new IllegalTaskArgumentsException(
                    "All sub task completed. Set status to COMPLETED and Readiness to 100");
        }

        if (subTask != null
                && !completedDate.equals(
                        subTask.stream()
                                .map(TaskObject::getCompletedDate)
                                .filter(Objects::nonNull)
                                .max(LocalDate::compareTo)
                                .orElse(null))) {
            throw new IllegalTaskArgumentsException(
                    "Completed date must equals max completed date in sub task or null");
        }

        this.createDate = createDate;
        this.startDate = startDate;
        this.completedDate = completedDate;
        this.responsiblePerson = responsiblePerson;
        this.taskState = taskState;
        this.readiness = readiness;
    }

    /**
     * Метод, который возвращает под.задачи. Если для данной задачи нет под.задачи, то необходимо вернуть null.
     * @return List<? extends TaskObject>
     */
    public abstract List<? extends TaskObject> getSubTask();
}
