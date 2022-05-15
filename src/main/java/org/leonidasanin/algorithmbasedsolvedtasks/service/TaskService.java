package org.leonidasanin.algorithmbasedsolvedtasks.service;

import org.leonidasanin.algorithmbasedsolvedtasks.dao.TaskInputsDAO;
import org.leonidasanin.algorithmbasedsolvedtasks.exception.TaskException;
import org.leonidasanin.algorithmbasedsolvedtasks.model.Input;
import org.leonidasanin.algorithmbasedsolvedtasks.model.Task;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private static final Task EMPTY_TASK = new Task().setId(0).setName("No Tasks");

    private final TaskInputsDAO taskInputsDAO;
    private List<Task> tasks;

    public TaskService(List<Task> tasks, TaskInputsDAO taskInputsDAO) {
        this.tasks = tasks;
        this.taskInputsDAO = taskInputsDAO;
    }

    public List<Task> getAllTasks() {
        if (tasks.isEmpty()) {
            return List.of(EMPTY_TASK);
        }
        return tasks;
    }

    public Task getTaskById(int taskId) {
        if (taskId == 0) {
            return EMPTY_TASK;
        }
        return tasks.stream()
                .filter(t -> t.getId() == taskId)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public Task getTaskByName(String name) {
        return tasks.stream()
                .filter(t -> t.getName().equals(name))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public Task getDefaultTask() {
        if (tasks.isEmpty()) {
            return EMPTY_TASK;
        }
        return tasks.get(0);
    }

    public List<Input> getInputsByTaskId(int taskId) {
        var inputs = taskInputsDAO.getInputsByTaskId(taskId)
                .stream()
                .distinct()
                .collect(Collectors.toList());
        Collections.reverse(inputs);
        return inputs.stream()
                .limit(10)
                .toList();
    }

    public String getInputById(int inputId) {
        return taskInputsDAO.getInputById(inputId);
    }

    public void saveInputForTask(int taskId, String input) throws TaskException {
        var task = getTaskById(taskId);
        if (task.isInputCorrect(input)) {
            taskInputsDAO.save(taskId, input);
        } else {
            throw new TaskException("Incorrect Input", task);
        }
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
