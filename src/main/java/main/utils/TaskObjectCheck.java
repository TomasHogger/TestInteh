package main.utils;

import main.constants.ReadinessConstants;
import main.model.state.TaskState;
import main.model.task.abstr.TaskObject;

import java.util.List;

public class TaskObjectCheck {

    private TaskObjectCheck() { }

    public static boolean isNotAllTaskObjectCompletedOrTaskStateAndReadinessCorrect(
            List<? extends TaskObject> list,
            byte readiness,
            TaskState taskState) {
        return list.stream().anyMatch(task -> task.getTaskState() != TaskState.COMPLETED)
                || (taskState == TaskState.COMPLETED && readiness == ReadinessConstants.FULL_COMPLETED);
    }

}
