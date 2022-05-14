package org.leonidasanin.algorithmbasedsolvedtasks.exception;

import org.leonidasanin.algorithmbasedsolvedtasks.model.Task;

public class NoTaskWithGivenIdException extends Exception {
    private Task previousTask;

    public NoTaskWithGivenIdException() {
    }

    public NoTaskWithGivenIdException(String message, Task previousTask) {
        super(message);
        this.previousTask = previousTask;
    }

    public Task getPreviousTask() {
        return previousTask;
    }
}
