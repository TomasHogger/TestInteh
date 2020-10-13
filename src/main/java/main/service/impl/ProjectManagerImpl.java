package main.service.impl;

import main.model.task.impl.Project;
import main.model.task.abstr.Task;
import main.service.abstr.ProjectManager;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import static main.model.task.abstr.TaskObject.FULL_COMPLETED;

public class ProjectManagerImpl implements ProjectManager {

    /**
     * Метод для расчёта ПДЗ списка проектов.
     * @param projects проекты
     * @return LocalDate
     */
    @Override
    public LocalDate calculatePDZForProjects(final List<Project> projects) {
        return calculatePDZRelativelyDate(projects
                .stream()
                .flatMap(project -> project.getRequirements().stream())
                .flatMap(requirement -> requirement.getTasks().stream())
                .mapToInt(this::calculatePDZ)
                .max()
                .orElse(0), LocalDate.now());

    }

    /**
     * Расчёт пдз для одной задачи
     * @param task задача
     * @return int
     */
    private int calculatePDZ(Task task) {
        double coef = 1/task.getCoef();
        int readiness = FULL_COMPLETED - task.getReadiness();
        int countOfDay = Math.abs(Period.between(LocalDate.now(), task.getCreateDate()).getDays());
        return (int) Math.ceil((coef * readiness * countOfDay) / task.getReadiness());
    }

    /**
     * Расчёт пдз относительно указанной даты
     * @param pdz пдз
     * @param date дата
     * @return LocalDate
     */
    private LocalDate calculatePDZRelativelyDate(int pdz, LocalDate date) {
        return date.plusDays(pdz);
    }
}
