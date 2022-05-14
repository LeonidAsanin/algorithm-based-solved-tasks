package org.leonidasanin.algorithmbasedsolvedtasks.service;

import org.leonidasanin.algorithmbasedsolvedtasks.exception.DownloadFromFileException;
import org.leonidasanin.algorithmbasedsolvedtasks.exception.NoTaskWithGivenIdException;
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
        String input = "";

        var reader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()));
        reader.readLine(); // read taskId string
        input = reader.readLine();

        return input;
    }

    public Task getTaskFromFileElseById(MultipartFile multipartFile, int taskId) throws DownloadFromFileException,
                                                                                        NoTaskWithGivenIdException {
        int taskIdFromFile;

        try {
            var reader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()));
            taskIdFromFile = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            throw new DownloadFromFileException("Error while downloading from the file", taskService.getTaskById(taskId));
        }

        try {
            return taskService.getTaskById(taskIdFromFile);
        } catch (NoSuchElementException e) {
            throw new NoTaskWithGivenIdException("There is no task with given id", taskService.getTaskById(taskId));
        }
    }
}
