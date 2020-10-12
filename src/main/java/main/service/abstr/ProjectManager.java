package main.service.abstr;

import main.model.task.abstr.Project;

import java.time.LocalDate;
import java.util.List;

public interface ProjectManager {
    LocalDate calculatePDZForProjects(List<Project> list);
}
