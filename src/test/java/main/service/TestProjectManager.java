package main.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import main.model.employee.Employee;
import main.model.state.TaskState;
import main.model.task.abstr.TaskObject;
import main.model.task.impl.*;
import main.service.abstr.ProjectManager;
import main.service.impl.ProjectManagerImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import testUtil.JsonLogger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestProjectManager {

    private TestResult testResult;

    @Before
    public void before() {
        testResult = new TestResult();
        testResult.setStartDateTime(LocalDateTime.now());
    }

    @After
    public void after() {
        testResult.setEndDateTime(LocalDateTime.now());
        JsonLogger.log(testResult);
    }

    @Test
    public void testOneProject() {
        testResult.setTestName("testOneProject");

        ProjectManager projectManager = new ProjectManagerImpl();

        Requirement requirement1 = new Requirement(
                LocalDate.now().plusDays(-5),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(4),
                TaskState.IN_WORK,
                new Employee(""),
                (byte) 50,
                Arrays.asList(
                        new FeatureTask(
                                LocalDate.now().plusDays(-2),
                                LocalDate.now().plusDays(1),
                                LocalDate.now().plusDays(4),
                                TaskState.IN_WORK,
                                new Employee(""),
                                (byte) 20),
                        new BugTask(
                                LocalDate.now().plusDays(-4),
                                LocalDate.now().plusDays(1),
                                LocalDate.now().plusDays(3),
                                TaskState.IN_WORK,
                                new Employee(""),
                                (byte) 40)));

        Requirement requirement2 = new Requirement(
                LocalDate.now().plusDays(-2),
                LocalDate.now().plusDays(-1),
                LocalDate.now().plusDays(5),
                TaskState.IN_WORK,
                new Employee(""),
                (byte) 40,
                Arrays.asList(
                        new BugTask(
                                LocalDate.now().plusDays(-1),
                                LocalDate.now().plusDays(1),
                                LocalDate.now().plusDays(5),
                                TaskState.IN_WORK,
                                new Employee(""),
                                (byte) 10),
                        new BugTask(
                                LocalDate.now().plusDays(-2),
                                LocalDate.now().plusDays(-1),
                                LocalDate.now(),
                                TaskState.COMPLETED,
                                new Employee(""),
                                (byte) 100)));

        Project project = new Project(
                LocalDate.now().plusDays(-3),
                LocalDate.now().plusDays(-2),
                LocalDate.now().plusDays(5),
                TaskState.IN_WORK,
                new Employee(""),
                (byte) 10,
                Arrays.asList(requirement1, requirement2));

        List<Project> projects = Collections.singletonList(project);

        testResult.entityCount = getCountTask(projects);

        LocalDate expected = LocalDate.now().plusDays(12);
        LocalDate actual = projectManager.calculatePDZForProjects(projects);

        Assert.assertEquals(expected, actual);
        testResult.returnValue = actual;
    }

    @Test
    public void testTwoProject() {
        testResult.setTestName("testTwoProject");

        ProjectManager projectManager = new ProjectManagerImpl();

        Requirement requirement1 = new Requirement(
                LocalDate.now().plusDays(-3),
                LocalDate.now().plusDays(-1),
                LocalDate.now().plusDays(10),
                TaskState.IN_WORK,
                new Employee(""),
                (byte) 50,
                Arrays.asList(
                        new FeatureTask(LocalDate.now(),
                                LocalDate.now().plusDays(1),
                                LocalDate.now().plusDays(4),
                                TaskState.IN_WORK,
                                new Employee(""),
                                (byte) 20),
                        new BugTask(LocalDate.now(),
                                LocalDate.now().plusDays(1),
                                LocalDate.now().plusDays(10),
                                TaskState.IN_WORK,
                                new Employee(""),
                                (byte) 40)));

        Project project1 = new Project(
                LocalDate.now().plusDays(-3),
                LocalDate.now().plusDays(-2),
                LocalDate.now().plusDays(10),
                TaskState.IN_WORK,
                new Employee(""),
                (byte) 10,
                Collections.singletonList(requirement1));

        Requirement requirement2 = new Requirement(
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(4),
                TaskState.IN_WORK,
                new Employee(""),
                (byte) 50,
                Arrays.asList(
                        new FeatureTask(LocalDate.now(),
                                LocalDate.now().plusDays(1),
                                LocalDate.now().plusDays(4),
                                TaskState.IN_WORK,
                                new Employee(""),
                                (byte) 20),
                        new ImprovementTask(LocalDate.now(),
                                LocalDate.now().plusDays(1),
                                LocalDate.now().plusDays(3),
                                TaskState.IN_WORK,
                                new Employee(""),
                                (byte) 40),
                        new BugTask(LocalDate.now(),
                                LocalDate.now().plusDays(1),
                                LocalDate.now().plusDays(3),
                                TaskState.IN_WORK,
                                new Employee(""),
                                (byte) 40)));

        Requirement requirement3 = new Requirement(
                LocalDate.now().plusDays(-2),
                LocalDate.now().plusDays(-1),
                LocalDate.now().plusDays(5),
                TaskState.IN_WORK,
                new Employee(""),
                (byte) 40,
                Arrays.asList(
                        new BugTask(LocalDate.now(),
                                LocalDate.now().plusDays(1),
                                LocalDate.now().plusDays(5),
                                TaskState.IN_WORK,
                                new Employee(""),
                                (byte) 10),
                        new BugTask(LocalDate.now().plusDays(-2),
                                LocalDate.now().plusDays(-1),
                                LocalDate.now(),
                                TaskState.COMPLETED,
                                new Employee(""),
                                (byte) 100)));

        Requirement requirement4 = new Requirement(
                LocalDate.now().plusDays(-4),
                LocalDate.now().plusDays(-1),
                LocalDate.now().plusDays(15),
                TaskState.IN_WORK,
                new Employee(""),
                (byte) 40,
                Collections.singletonList(
                        new ImprovementTask(
                                LocalDate.now().plusDays(-2),
                                LocalDate.now().plusDays(-1),
                                LocalDate.now().plusDays(15),
                                TaskState.IN_WORK,
                                new Employee(""),
                                (byte) 90)));

        Project project2 = new Project(
                LocalDate.now().plusDays(-3),
                LocalDate.now().plusDays(-2),
                LocalDate.now().plusDays(15),
                TaskState.IN_WORK,
                new Employee(""),
                (byte) 10,
                Arrays.asList(requirement2, requirement3, requirement4));

        List<Project> projects = Arrays.asList(project1, project2);

        testResult.entityCount = getCountTask(projects);

        LocalDate expected = LocalDate.now().plusDays(1);
        LocalDate actual = projectManager.calculatePDZForProjects(projects);

        Assert.assertEquals(expected, actual);

        testResult.returnValue = actual;
    }

    private int getCountTask(List<? extends TaskObject> list) {
        int count = 0;
        for (TaskObject taskObject : list) {
            count++;
            if (taskObject.getSubTask() != null) {
                count += getCountTask(taskObject.getSubTask());
            }
        }
        return count;
    }

    @Data
    private class TestResult extends JsonLogger.TestResult {
        @JsonProperty("EntityCount")
        private Integer entityCount;
        @JsonProperty("ReturnValue")
        @JsonDeserialize(using = JsonLogger.LocalDateDeserializerForResult.class)
        @JsonSerialize(using = JsonLogger.LocalDateSerializerForResult.class)
        private LocalDate returnValue;
    }
}
