package org.leonidasanin.algorithmbasedsolvedtasks.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.leonidasanin.algorithmbasedsolvedtasks.exception.TaskException;
import org.leonidasanin.algorithmbasedsolvedtasks.model.OptimalSemiMagicSquare3By3Task;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FileHandlerServiceTests {

    FileHandlerService fileHandlerService;

    @Mock
    TaskService taskServiceMock;

    @Mock
    MockMultipartFile multipartFileMock;

    @BeforeEach
    void setUp() {
        fileHandlerService = new FileHandlerService(taskServiceMock);
    }

    @Test
    void getInputFromFile() throws Exception {
        //given
        var input = "1 2 3 4 5 6 7 8 9";
        var fileContent1 = "TaskName" + "\n" + input + "\n";
        var fileContent2 = "TaskName";
        var file1 = new MockMultipartFile(
                "file",
                "fileName.txt",
                MediaType.TEXT_PLAIN_VALUE,
                fileContent1.getBytes()
        );
        var file2 = new MockMultipartFile(
                "file",
                "fileName.txt",
                MediaType.TEXT_PLAIN_VALUE,
                fileContent2.getBytes()
        );

        //when
        var result1 = fileHandlerService.getInputFromFile(file1);
        var result2 = fileHandlerService.getInputFromFile(file2);
        Mockito.when(multipartFileMock.getInputStream()).thenThrow(new IOException());
        Executable throwing = () -> fileHandlerService.getInputFromFile(multipartFileMock);

        //then
        assertAll(
                () -> assertEquals(input, result1),
                () -> assertEquals("", result2),
                () -> assertThrows(IOException.class, throwing)
        );
    }

    @Test
    void getTaskFromFileElseById() throws Exception {
        //given
        var input = "1 2 3 4 5 6 7 8 9";
        var task = new OptimalSemiMagicSquare3By3Task();
        var taskName = task.getName();
        var fileContent = taskName + "\n" + input + "\n";
        var file = new MockMultipartFile(
                "file",
                "fileName.txt",
                MediaType.TEXT_PLAIN_VALUE,
                fileContent.getBytes()
        );

        //when
        Mockito.when(taskServiceMock.getTaskByName(taskName)).thenReturn(task);
        var result = fileHandlerService.getTaskFromFileElseById(file, 2);
        Mockito.when(multipartFileMock.getInputStream()).thenThrow(new IOException());
        Executable throwing1 = () -> fileHandlerService.getTaskFromFileElseById(multipartFileMock, 2);
        Mockito.when(taskServiceMock.getTaskByName(taskName)).thenThrow(new NoSuchElementException());
        Executable throwing2 = () -> fileHandlerService.getTaskFromFileElseById(file, 2);

        //then
        assertAll(
                () -> assertEquals(task, result),
                () -> assertThrows(TaskException.class, throwing1),
                () -> assertThrows(TaskException.class, throwing2)
        );
    }
}