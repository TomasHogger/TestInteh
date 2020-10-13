package main.model;

import main.exception.IllegalCompletedDateException;
import main.exception.StatusAndReadinessIncorrect;
import main.model.employee.Employee;
import main.model.state.TaskState;
import main.model.task.impl.BugTask;
import main.model.task.impl.Project;
import main.model.task.impl.Requirement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import testUtil.JsonLogger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class TestProject {

    private JsonLogger.TestResult testResult;

    @Before
    public void before() {
        testResult = new JsonLogger.TestResult();
        testResult.setStartDateTime(LocalDateTime.now());
    }

    @After
    public void after() {
        testResult.setEndDateTime(LocalDateTime.now());
        JsonLogger.log(testResult);
    }

    @Test(expected = StatusAndReadinessIncorrect.class)
    public void testWrongReadinessValueAndTaskStatusForProjectWithAllCompletedRequirements() {
        testResult.setTestName("testWrongReadinessValueAndTaskStatusForProjectWithAllCompletedRequirements");

        Requirement requirement1 = new Requirement(
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2),
                TaskState.COMPLETED,
                new Employee(""),
                (byte) 100,
                Arrays.asList(
                        new BugTask(LocalDate.now(),
                                LocalDate.now().plusDays(1),
                                LocalDate.now().plusDays(2),
                                TaskState.COMPLETED,
                                new Employee(""),
                                (byte) 100),
                        new BugTask(LocalDate.now(),
                                LocalDate.now().plusDays(1),
                                LocalDate.now().plusDays(2),
                                TaskState.COMPLETED,
                                new Employee(""),
                                (byte) 100)));

        Requirement requirement2 = new Requirement(
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(3),
                TaskState.COMPLETED,
                new Employee(""),
                (byte) 100,
                Arrays.asList(
                        new BugTask(LocalDate.now(),
                                LocalDate.now().plusDays(1),
                                LocalDate.now().plusDays(3),
                                TaskState.COMPLETED,
                                new Employee(""),
                                (byte) 100),
                        new BugTask(LocalDate.now(),
                                LocalDate.now().plusDays(1),
                                LocalDate.now().plusDays(2),
                                TaskState.COMPLETED,
                                new Employee(""),
                                (byte) 100)));

        new Project(LocalDate.now(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(3),
                TaskState.IN_WORK,
                new Employee(""),
                (byte) 10,
                Arrays.asList(requirement1, requirement2));
    }

    @Test(expected = IllegalCompletedDateException.class)
    public void testWrongCompletedDateForProject() {
        testResult.setTestName("testWrongCompletedDateForProject");

        Requirement requirement1 = new Requirement(
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2),
                TaskState.COMPLETED,
                new Employee(""),
                (byte) 100,
                Arrays.asList(
                        new BugTask(LocalDate.now(),
                                LocalDate.now().plusDays(1),
                                LocalDate.now().plusDays(2),
                                TaskState.COMPLETED,
                                new Employee(""),
                                (byte) 100),
                        new BugTask(LocalDate.now(),
                                LocalDate.now().plusDays(1),
                                LocalDate.now().plusDays(2),
                                TaskState.COMPLETED,
                                new Employee(""),
                                (byte) 100)));

        Requirement requirement2 = new Requirement(
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(3),
                TaskState.COMPLETED,
                new Employee(""),
                (byte) 100,
                Arrays.asList(
                        new BugTask(LocalDate.now(),
                                LocalDate.now().plusDays(1),
                                LocalDate.now().plusDays(3),
                                TaskState.COMPLETED,
                                new Employee(""),
                                (byte) 100),
                        new BugTask(LocalDate.now(),
                                LocalDate.now().plusDays(1),
                                LocalDate.now().plusDays(2),
                                TaskState.COMPLETED,
                                new Employee(""),
                                (byte) 100)));

        new Project(LocalDate.now(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2),
                TaskState.COMPLETED,
                new Employee(""),
                (byte) 100,
                Arrays.asList(requirement1, requirement2));
    }
}
