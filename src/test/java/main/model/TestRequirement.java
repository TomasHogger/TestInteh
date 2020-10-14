package main.model;

import main.exception.IllegalTaskArgumentsException;
import main.model.employee.Employee;
import main.model.state.TaskState;
import main.model.task.abstr.Task;
import main.model.task.impl.BugTask;
import main.model.task.impl.Requirement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import testUtil.JsonLogger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class TestRequirement {

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


    @Test(expected = IllegalTaskArgumentsException.class)
    public void testWrongReadinessValueAndTaskStatusForRequirementWithAllCompletedTasks() {

        testResult.setTestName("testWrongReadinessValueAndTaskStatusForRequirementWithAllCompletedTasks");

        Task task1 = new BugTask(LocalDate.now(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2),
                TaskState.COMPLETED,
                new Employee(""),
                (byte) 100);

        Task task2 = new BugTask(LocalDate.now(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(3),
                TaskState.COMPLETED,
                new Employee(""),
                (byte) 100);


        new Requirement(
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(3),
                TaskState.IN_WORK,
                new Employee(""),
                (byte) 10,
                Arrays.asList(task1, task2));
    }

    @Test(expected = IllegalTaskArgumentsException.class)
    public void testWrongCompletedDateForRequirement() {

        testResult.setTestName("testWrongCompletedDateForRequirement");

        Task task1 = new BugTask(LocalDate.now(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2),
                TaskState.COMPLETED,
                new Employee(""),
                (byte) 100);

        Task task2 = new BugTask(LocalDate.now(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(3),
                TaskState.COMPLETED,
                new Employee(""),
                (byte) 100);


        new Requirement(
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2),
                TaskState.COMPLETED,
                new Employee(""),
                (byte) 100,
                Arrays.asList(task1, task2));
    }

}
