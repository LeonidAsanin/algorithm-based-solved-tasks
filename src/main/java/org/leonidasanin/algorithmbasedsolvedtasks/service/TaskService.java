package org.leonidasanin.algorithmbasedsolvedtasks.service;

import org.leonidasanin.algorithmbasedsolvedtasks.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {
    private final List<Task> tasks;

    public TaskService(List<Task> tasks) {
         this.tasks = tasks;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTaskById(int taskId) {
        //TODO: implement getTaskById() method of TaskService class
        if (taskId == 1) return tasks.get(0);
        if (taskId == 2) return tasks.get(1);
        throw new NoSuchElementException();
    }

    public Task getDefaultTask() {
        //TODO: implement getDefaultTask() method of TaskService class
        return tasks.get(0);
    }

    public void saveInputForTask(int taskId, String input) {
        //TODO: implement saveInputForTask() method of TaskService class
        System.out.println("input [" + input + "] for task with id " + taskId + " was saved");
    }

    public List<String> getInputsByTaskId(int taskId) {
        //TODO: implement getInputsByTaskId() method of TaskService class
        if (taskId == 1) return List.of("input11", "input12");
        if (taskId == 2) return List.of("input21", "input22ew   qwqw", "input23");
        throw new NoSuchElementException();
    }
}
