package org.leonidasanin.algorithmbasedsolvedtasks.exception;

import org.leonidasanin.algorithmbasedsolvedtasks.model.Task;

public class TaskException extends Exception {
    private final Task previousTask;

    public TaskException(String message, Task previousTask) {
        super(message);
        this.previousTask = previousTask;
    }

    public Task getPreviousTask() {
        return previousTask;
    }
}
