package org.leonidasanin.algorithmbasedsolvedtasks.service;

import org.leonidasanin.algorithmbasedsolvedtasks.exception.TaskException;
import org.leonidasanin.algorithmbasedsolvedtasks.model.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

@Service
public class FileHandlerService {
    private final TaskService taskService;

    public FileHandlerService(TaskService taskService) {
        this.taskService = taskService;
    }

    public String getInputFromFile(MultipartFile multipartFile) throws IOException {
        String input;

        var reader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()));
        reader.readLine(); // read task name
        input = reader.readLine();

        return input;
    }

    public Task getTaskFromFileElseById(MultipartFile multipartFile, int taskId) throws TaskException {
        String taskNameFromFile;

        try {
            var reader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()));
            taskNameFromFile = reader.readLine();
        } catch (IOException e) {
            throw new TaskException("Error while downloading from the file", taskService.getTaskById(taskId));
        }

        try {
            return taskService.getTaskByName(taskNameFromFile);
        } catch (NoSuchElementException e) {
            throw new TaskException("There is no task with given name", taskService.getTaskById(taskId));
        }
    }
}
