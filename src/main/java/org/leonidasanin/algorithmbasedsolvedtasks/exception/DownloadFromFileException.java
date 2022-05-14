package org.leonidasanin.algorithmbasedsolvedtasks.exception;

import org.leonidasanin.algorithmbasedsolvedtasks.model.Task;

public class DownloadFromFileException extends Exception {
    private Task previousTask;

    public DownloadFromFileException() {
    }

    public DownloadFromFileException(String message, Task previousTask) {
        super(message);
        this.previousTask = previousTask;
    }

    public Task getPreviousTask() {
        return previousTask;
    }
}
