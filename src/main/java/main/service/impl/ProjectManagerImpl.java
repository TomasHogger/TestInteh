package main.service.impl;

import main.constants.ReadinessConstants;
import main.model.task.abstr.Project;
import main.model.task.abstr.Task;
import main.service.abstr.ProjectManager;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class ProjectManagerImpl implements ProjectManager {

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

    private int calculatePDZ(Task task) {
        double coef = 1/task.getCoef();
        int readiness = ReadinessConstants.FULL_COMPLETED - task.getReadiness();
        int countOfDay = Period.between(LocalDate.now(), task.getCreateDate()).getDays();
        return (int) Math.ceil((coef * readiness * countOfDay) / task.getReadiness());
    }

    private LocalDate calculatePDZRelativelyDate(int pdz, LocalDate date) {
        return date.plusDays(pdz);
    }
}
