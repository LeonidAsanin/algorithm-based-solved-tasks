package org.leonidasanin.algorithmbasedsolvedtasks.controller.mainController;

import org.junit.jupiter.api.Test;
import org.leonidasanin.algorithmbasedsolvedtasks.model.OptimalSemiMagicSquare3By3Task;
import org.leonidasanin.algorithmbasedsolvedtasks.service.TaskService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ExportToFileTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskService taskServiceMock;

    @Test
    void test() throws Exception {
        //given
        var input = "1 2 3 4 5 6 7 8 9";
        var task = new OptimalSemiMagicSquare3By3Task();
        var taskId = task.getId();
        var taskName = task.getName();
        var fileContent = taskName + "\n" + input + "\n";
        var resourceBytes = fileContent.getBytes();

        //when
        Mockito.when(taskServiceMock.getTaskById(taskId))
                .thenReturn(task);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/export")
                        .param("input", input)
                        .param("task", String.valueOf(taskId))
                )
        //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_OCTET_STREAM))
                .andExpect(MockMvcResultMatchers.content().bytes(resourceBytes))
                .andExpect(MockMvcResultMatchers.header().exists(HttpHeaders.CONTENT_DISPOSITION));
    }
}
