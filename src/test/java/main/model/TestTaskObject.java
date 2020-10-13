package main.model;

import main.exception.DateTaskException;
import main.exception.StatusAndReadinessIncorrect;
import main.model.employee.Employee;
import main.model.state.TaskState;
import main.model.task.abstr.TaskObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import testUtil.JsonLogger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TestTaskObject {

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


    @Test(expected = DateTaskException.class)
    public void testWrongCreateDate() {

        testResult.setTestName("testWrongCreateDate");

        new TaskObject(LocalDate.now(),
                LocalDate.now().plusDays(-1),
                LocalDate.now().plusDays(2),
                TaskState.IN_WORK,
                new Employee(""),
                (byte) 1,
                null) {
            @Override
            public List<? extends TaskObject> getSubTask() {
                return null;
            }
        };
    }

    @Test(expected = DateTaskException.class)
    public void testWrongStartDate() {

        testResult.setTestName("testWrongStartDate");

        new TaskObject(LocalDate.now(),
                LocalDate.now().plusDays(3),
                LocalDate.now().plusDays(2),
                TaskState.IN_WORK,
                new Employee(""),
                (byte) 1,
                null) {
            @Override
            public List<? extends TaskObject> getSubTask() {
                return null;
            }
        };
    }

    @Test(expected = DateTaskException.class)
    public void testWrongCompletedDate() {

        testResult.setTestName("testWrongCompletedDate");

        new TaskObject(LocalDate.now(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(-2),
                TaskState.IN_WORK,
                new Employee(""),
                (byte) 1,
                null) {
            @Override
            public List<? extends TaskObject> getSubTask() {
                return null;
            }
        };
    }

    @Test(expected = StatusAndReadinessIncorrect.class)
    public void testWrongCreateDateWithNotNewState() {

        testResult.setTestName("testWrongCreateDateWithNotNewState");

        new TaskObject(LocalDate.now(),
                null,
                LocalDate.now().plusDays(2),
                TaskState.IN_WORK,
                new Employee(""),
                (byte) 1,
                null) {
            @Override
            public List<? extends TaskObject> getSubTask() {
                return null;
            }
        };
    }

    @Test(expected = StatusAndReadinessIncorrect.class)
    public void testWrongCompletedDateWithCompletedStatus() {

        testResult.setTestName("testWrongCompletedDateWithCompletedStatus");

        new TaskObject(LocalDate.now(),
                LocalDate.now().plusDays(1),
                null,
                TaskState.COMPLETED,
                new Employee(""),
                (byte) 100,
                null) {
            @Override
            public List<? extends TaskObject> getSubTask() {
                return null;
            }
        };
    }

    @Test(expected = StatusAndReadinessIncorrect.class)
    public void testWrongReadinessValueWithCompletedStatus() {

        testResult.setTestName("testWrongReadinessValueWithCompletedStatus");

        new TaskObject(LocalDate.now(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2),
                TaskState.COMPLETED,
                new Employee(""),
                (byte) 10,
                null) {
            @Override
            public List<? extends TaskObject> getSubTask() {
                return null;
            }
        };
    }

}
