package org.leonidasanin.algorithmbasedsolvedtasks.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.leonidasanin.algorithmbasedsolvedtasks.dao.TaskInputsDAO;
import org.leonidasanin.algorithmbasedsolvedtasks.exception.TaskException;
import org.leonidasanin.algorithmbasedsolvedtasks.model.Input;
import org.leonidasanin.algorithmbasedsolvedtasks.model.OptimalSemiMagicSquare3By3Task;
import org.leonidasanin.algorithmbasedsolvedtasks.model.SortedStringArrayIntersectionTask;
import org.leonidasanin.algorithmbasedsolvedtasks.model.Task;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTests {

    List<Task> tasks;
    TaskService taskService;
    TaskService emptyTaskService;

    @Mock
    TaskInputsDAO taskInputsDAOMock;

    @BeforeEach
    void setUp() {
        tasks = List.of(new OptimalSemiMagicSquare3By3Task(), new SortedStringArrayIntersectionTask());
        taskService = new TaskService(tasks, taskInputsDAOMock);
        emptyTaskService = new TaskService(List.of(), taskInputsDAOMock);
    }

    @Test
    void getAllTasks() {
        //given
        var emptyTask = new Task().setId(0);

        //when
        var result1 = taskService.getAllTasks();
        var result2 = emptyTaskService.getAllTasks();

        //then
        assertAll(
                () -> assertEquals(tasks, result1),
                () -> {
                    assertEquals(1, result2.size());
                    assertEquals(emptyTask, result2.get(0));
                }
        );
    }

    @Test
    void getTaskById() {
        //given
        var emptyTask = new Task().setId(0);

        //when
        var result0 = taskService.getTaskById(0);
        var result1 = taskService.getTaskById(1);
        var result2 = taskService.getTaskById(2);

        //then
        assertAll(
                () -> assertEquals(emptyTask, result0),
                () -> assertEquals(tasks.get(0), result1),
                () -> assertEquals(tasks.get(1), result2)
        );
    }

    @Test
    void getTaskByName() {
        //given
        var task1 = tasks.get(0);
        var task2 = tasks.get(1);

        //when
        var result1 = taskService.getTaskByName(task1.getName());
        var result2 = taskService.getTaskByName(task2.getName());
        Executable throwing = () -> taskService.getTaskByName("WRONG_NAME");

        //then
        assertAll(
                () -> assertEquals(task1, result1),
                () -> assertEquals(task2, result2),
                () -> assertThrows(NoSuchElementException.class, throwing)
        );
    }

    @Test
    void getDefaultTask() {
        //given
        var emptyTask = new Task().setId(0);

        //when
        var result1 = taskService.getDefaultTask();
        var result2 = emptyTaskService.getDefaultTask();

        //then
        assertAll(
                () -> assertEquals(tasks.get(0), result1),
                () -> assertEquals(emptyTask, result2)
        );
    }

    @Test
    void saveInputForTask() throws TaskException {
        //given
        var taskId = 1;
        var correctInput = "1 2 3 4 5 6 7 8 9";
        var incorrectInput = "1 2 3 4 5 6 7 8 9 10";

        //when
        taskService.saveInputForTask(taskId, correctInput);
        Executable throwing = () -> taskService.saveInputForTask(taskId, incorrectInput);

        //then
        assertAll(
                () -> Mockito.verify(taskInputsDAOMock).save(taskId, correctInput),
                () -> assertThrows(TaskException.class, throwing)
        );
    }

    @Test
    void getInputsByTaskId() {
        //given
        var taskId = 3;
        var inputs = List.of(
                new Input(1, "1"),
                new Input(2, "2"),
                new Input(3, "3"),
                new Input(4, "4"),
                new Input(5, "5"),
                new Input(6, "6"),
                new Input(7, "7"),
                new Input(8, "8"),
                new Input(9, "9"),
                new Input(10, "10"),
                new Input(11, "10")
        );

        //when
        Mockito.when(taskInputsDAOMock.getInputsByTaskId(taskId))
                .thenReturn(inputs);
        var result = taskService.getInputsByTaskId(taskId);

        //then
        assertEquals(10, result.size());
        assertAll(
                () -> assertEquals(10, result.get(0).getId()),
                () -> assertEquals(9, result.get(1).getId()),
                () -> assertEquals(8, result.get(2).getId()),
                () -> assertEquals(7, result.get(3).getId()),
                () -> assertEquals(6, result.get(4).getId()),
                () -> assertEquals(5, result.get(5).getId()),
                () -> assertEquals(4, result.get(6).getId()),
                () -> assertEquals(3, result.get(7).getId()),
                () -> assertEquals(2, result.get(8).getId()),
                () -> assertEquals(1, result.get(9).getId())
        );
    }

    @Test
    void getInputById() {
        //given
        var inputId = 1;
        var input = "1 2 3 4 5 6 7 8 9";

        //when
        Mockito.when(taskInputsDAOMock.getInputById(inputId))
                .thenReturn(input);
        var result = taskService.getInputById(inputId);

        //then
        assertEquals(input, result);
    }

    @Test
    void setTasks() {
        //given
        var newTasks = List.of(new Task().setId(3));

        //when
        emptyTaskService.setTasks(newTasks);

        //then
        var result = emptyTaskService.getAllTasks();
        assertEquals(newTasks, result);
    }
}