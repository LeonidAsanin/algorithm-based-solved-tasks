package org.leonidasanin.algorithmbasedsolvedtasks.controller.mainController;

import org.junit.jupiter.api.Test;
import org.leonidasanin.algorithmbasedsolvedtasks.model.Input;
import org.leonidasanin.algorithmbasedsolvedtasks.model.OptimalSemiMagicSquare3By3Task;
import org.leonidasanin.algorithmbasedsolvedtasks.model.SortedStringArrayIntersectionTask;
import org.leonidasanin.algorithmbasedsolvedtasks.service.TaskService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class GetMainPageTests {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskService taskServiceMock;

    @Test
    void testEmptyModelAttributes() throws Exception {
        //given
        var defaultTask = new OptimalSemiMagicSquare3By3Task();
        var tasks = List.of(defaultTask, new SortedStringArrayIntersectionTask());
        var inputs = List.of(new Input(1, "1 2 3 4 5 6 7 8 9"),
                             new Input(2, "9 8 7 6 5 4 3 2 1"));

        //when
        Mockito.when(taskServiceMock.getAllTasks())
                .thenReturn(tasks);
        Mockito.when(taskServiceMock.getDefaultTask())
                .thenReturn(new OptimalSemiMagicSquare3By3Task());
        Mockito.when(taskServiceMock.getInputsByTaskId(defaultTask.getId()))
                .thenReturn(inputs);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/")
                )
        //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attribute("tasks", tasks))
                .andExpect(MockMvcResultMatchers.model().attribute("chosenTaskId", defaultTask.getId()))
                .andExpect(MockMvcResultMatchers.model().attribute("taskDescription", defaultTask.getDescription()))
                .andExpect(MockMvcResultMatchers.model().attribute("input", defaultTask.getInputExample()))
                .andExpect(MockMvcResultMatchers.model().attribute("error", ""))
                .andExpect(MockMvcResultMatchers.model().attribute("result", ""))
                .andExpect(MockMvcResultMatchers.model().attribute("downloadedInputs", inputs));
    }

    @Test
    void testErrorModelAttribute() throws Exception {
        //given
        var defaultTask = new OptimalSemiMagicSquare3By3Task();
        var tasks = List.of(defaultTask, new SortedStringArrayIntersectionTask());
        var inputs = List.of(new Input(1, "1 2 3 4 5 6 7 8 9"),
                new Input(2, "9 8 7 6 5 4 3 2 1"));
        var error = "ERROR";

        //when
        Mockito.when(taskServiceMock.getAllTasks())
                .thenReturn(tasks);
        Mockito.when(taskServiceMock.getDefaultTask())
                .thenReturn(new OptimalSemiMagicSquare3By3Task());
        Mockito.when(taskServiceMock.getInputsByTaskId(defaultTask.getId()))
                .thenReturn(inputs);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/")
                        .flashAttr("error", error)
                )
        //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attribute("tasks", tasks))
                .andExpect(MockMvcResultMatchers.model().attribute("chosenTaskId", defaultTask.getId()))
                .andExpect(MockMvcResultMatchers.model().attribute("taskDescription", defaultTask.getDescription()))
                .andExpect(MockMvcResultMatchers.model().attribute("input", defaultTask.getInputExample()))
                .andExpect(MockMvcResultMatchers.model().attribute("error", error))
                .andExpect(MockMvcResultMatchers.model().attribute("result", ""))
                .andExpect(MockMvcResultMatchers.model().attribute("downloadedInputs", inputs));
    }

    @Test
    void testInputModelAttribute() throws Exception {
        //given
        var defaultTask = new OptimalSemiMagicSquare3By3Task();
        var tasks = List.of(defaultTask, new SortedStringArrayIntersectionTask());
        var inputs = List.of(new Input(1, "1 2 3 4 5 6 7 8 9"),
                new Input(2, "9 8 7 6 5 4 3 2 1"));
        var input = "1 2 3 6 5 4 7 8 9";

        //when
        Mockito.when(taskServiceMock.getAllTasks())
                .thenReturn(tasks);
        Mockito.when(taskServiceMock.getDefaultTask())
                .thenReturn(new OptimalSemiMagicSquare3By3Task());
        Mockito.when(taskServiceMock.getInputsByTaskId(defaultTask.getId()))
                .thenReturn(inputs);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/")
                        .flashAttr("input", input)
                )
                //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attribute("tasks", tasks))
                .andExpect(MockMvcResultMatchers.model().attribute("chosenTaskId", defaultTask.getId()))
                .andExpect(MockMvcResultMatchers.model().attribute("taskDescription", defaultTask.getDescription()))
                .andExpect(MockMvcResultMatchers.model().attribute("input", input))
                .andExpect(MockMvcResultMatchers.model().attribute("error", ""))
                .andExpect(MockMvcResultMatchers.model().attribute("result", ""))
                .andExpect(MockMvcResultMatchers.model().attribute("downloadedInputs", inputs));
    }

    @Test
    void testResultModelAttribute() throws Exception {
        //given
        var defaultTask = new OptimalSemiMagicSquare3By3Task();
        var tasks = List.of(defaultTask, new SortedStringArrayIntersectionTask());
        var inputs = List.of(new Input(1, "1 2 3 4 5 6 7 8 9"),
                new Input(2, "9 8 7 6 5 4 3 2 1"));
        var result = "RESULT";

        //when
        Mockito.when(taskServiceMock.getAllTasks())
                .thenReturn(tasks);
        Mockito.when(taskServiceMock.getDefaultTask())
                .thenReturn(new OptimalSemiMagicSquare3By3Task());
        Mockito.when(taskServiceMock.getInputsByTaskId(defaultTask.getId()))
                .thenReturn(inputs);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/")
                        .flashAttr("result", result)
                )
        //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attribute("tasks", tasks))
                .andExpect(MockMvcResultMatchers.model().attribute("chosenTaskId", defaultTask.getId()))
                .andExpect(MockMvcResultMatchers.model().attribute("taskDescription", defaultTask.getDescription()))
                .andExpect(MockMvcResultMatchers.model().attribute("input", defaultTask.getInputExample()))
                .andExpect(MockMvcResultMatchers.model().attribute("error", ""))
                .andExpect(MockMvcResultMatchers.model().attribute("result", result))
                .andExpect(MockMvcResultMatchers.model().attribute("downloadedInputs", inputs));
    }

    @Test
    void testTaskModelAttribute() throws Exception {
        //given
        var defaultTask = new OptimalSemiMagicSquare3By3Task();
        var task = new SortedStringArrayIntersectionTask();
        var tasks = List.of(defaultTask, task);
        var inputs = List.of(new Input(1, "1 2 3 4 5 6 7 8 9"),
                new Input(2, "9 8 7 6 5 4 3 2 1"));

        //when
        Mockito.when(taskServiceMock.getAllTasks())
                .thenReturn(tasks);
        Mockito.when(taskServiceMock.getInputsByTaskId(task.getId()))
                .thenReturn(inputs);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/")
                        .flashAttr("task", task)
                )
        //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attribute("tasks", tasks))
                .andExpect(MockMvcResultMatchers.model().attribute("chosenTaskId", task.getId()))
                .andExpect(MockMvcResultMatchers.model().attribute("taskDescription", task.getDescription()))
                .andExpect(MockMvcResultMatchers.model().attribute("input", task.getInputExample()))
                .andExpect(MockMvcResultMatchers.model().attribute("error", ""))
                .andExpect(MockMvcResultMatchers.model().attribute("result", ""))
                .andExpect(MockMvcResultMatchers.model().attribute("downloadedInputs", inputs));
    }
}
