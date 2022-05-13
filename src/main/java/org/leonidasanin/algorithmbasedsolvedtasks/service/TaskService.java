package org.leonidasanin.algorithmbasedsolvedtasks.service;

import org.leonidasanin.algorithmbasedsolvedtasks.model.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TaskService {
    private List<Task> tasks;

    public TaskService(List<Task> tasks) {
        this.tasks = tasks;
    }

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

    public String getInputFromFile(MultipartFile multipartFile) throws Exception {
        String input = "";

        var reader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()));
        reader.readLine(); // read taskId string
        input = reader.readLine();

        return input;
    }

    public Task getTaskFromFile(MultipartFile multipartFile) throws Exception {
        var taskId = getDefaultTask().getId();

        var reader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()));
        taskId = Integer.parseInt(reader.readLine());

        return getTaskById(taskId);
    }
}
