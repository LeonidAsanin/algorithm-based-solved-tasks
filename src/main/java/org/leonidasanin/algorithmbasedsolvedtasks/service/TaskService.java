package org.leonidasanin.algorithmbasedsolvedtasks.service;

import org.leonidasanin.algorithmbasedsolvedtasks.dao.TaskInputsDAO;
import org.leonidasanin.algorithmbasedsolvedtasks.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {
    private static final Task EMPTY_TASK = new Task().setId(0).setName("No Tasks");

    private final List<Task> tasks;
    private final TaskInputsDAO taskInputsDAO;

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

    public Task getDefaultTask() {
        if (tasks.isEmpty()) {
            return EMPTY_TASK;
        }
        return tasks.get(0);
    }

    public void saveInputForTask(int taskId, String input) {
        taskInputsDAO.save(taskId, input);
    }

    public List<String> getInputsByTaskId(int taskId) {
        return taskInputsDAO.getInputsByTaskId(taskId);
    }
}
