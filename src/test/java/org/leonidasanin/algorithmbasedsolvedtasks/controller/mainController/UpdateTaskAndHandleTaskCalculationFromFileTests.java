package org.leonidasanin.algorithmbasedsolvedtasks.controller.mainController;

import org.junit.jupiter.api.Test;
import org.leonidasanin.algorithmbasedsolvedtasks.model.OptimalSemiMagicSquare3By3Task;
import org.leonidasanin.algorithmbasedsolvedtasks.service.FileHandlerService;
import org.leonidasanin.algorithmbasedsolvedtasks.service.TaskService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class UpdateTaskAndHandleTaskCalculationFromFileTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskService taskServiceMock;

    @MockBean
    FileHandlerService fileHandlerServiceMock;

    @Test
    void testWithFile() throws Exception {
        //given
        var task = new OptimalSemiMagicSquare3By3Task();
        var taskId = task.getId();
        var taskName = task.getName();
        var input = "1 2 3 4 5 6 7 8 9";
        var result = task.solve(input);
        var fileContent = taskName + "\n" + input + "\n";
        var file = new MockMultipartFile(
                "file",
                "fileName.txt",
                MediaType.TEXT_PLAIN_VALUE,
                fileContent.getBytes()
        );

        //when
        Mockito.when(fileHandlerServiceMock.getTaskFromFileElseById(file, taskId))
                        .thenReturn(task);
        Mockito.when(fileHandlerServiceMock.getInputFromFile(file))
                .thenReturn(input);
        mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/")
                        .file(file)
                        .param("task", String.valueOf(taskId))
                )
        //then
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.flash().attribute("input", input))
                .andExpect(MockMvcResultMatchers.flash().attribute("result", result))
                .andExpect(MockMvcResultMatchers.flash().attribute("task", task))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    @Test
    void testEmptyOrNoFile() throws Exception {
        //given
        var task = new OptimalSemiMagicSquare3By3Task();
        var taskId = task.getId();
        var file = new MockMultipartFile("file", (byte[]) null);

        //when
        Mockito.when(taskServiceMock.getTaskById(taskId))
                .thenReturn(task);
        mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/")
                        .file(file)
                        .param("task", String.valueOf(taskId))
                )
        //then
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.flash().attribute("task", task))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }
}
