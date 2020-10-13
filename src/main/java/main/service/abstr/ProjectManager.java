package main.service.abstr;

import main.model.task.impl.Project;

import java.time.LocalDate;
import java.util.List;

public interface ProjectManager {
    /**
     * Метод для расчёта ПДЗ списка проектов.
     * @param list проекты
     * @return LocalDate
     */
    LocalDate calculatePDZForProjects(List<Project> list);
}
