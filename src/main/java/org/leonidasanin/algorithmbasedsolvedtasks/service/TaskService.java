package org.leonidasanin.algorithmbasedsolvedtasks.service;

import org.leonidasanin.algorithmbasedsolvedtasks.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TaskService {
    Task task1 = new Task().setId(1).setName("Task 1").setDescription("Description 1").setInputExample("Input example 1");
    Task task2 = new Task().setId(2).setName("Task 2").setDescription("Description 2").setInputExample("Input example 2");

    public List<Task> getAllTasks() {
        //TODO: implement getAllTasks() method of TaskService class
        var tasks = new ArrayList<Task>();
        Collections.addAll(tasks, task1, task2);
        return tasks;
    }

    public Task getTaskById(int taskId) {
        //TODO: implement getTaskById() method of TaskService class
        return taskId == 1 ? task1 : task2;
    }

    public Task getDefaultTask() {
        //TODO: implement getDefaultTask() method of TaskService class
        return task1;
    }

    public String solveTaskById(int taskId, String input) {
        //TODO: implement solveTaskById() method of TaskService class
        return "result of " + taskId + " with " + input;
    }

    public void saveInputForTask(int taskId, String input) {
        //TODO: implement saveInputForTask() method of TaskService class
        System.out.println("input [" + input + "] for task with id " + taskId + " was saved");
    }
}
