package org.leonidasanin.algorithmbasedsolvedtasks.controller.mainController;

import org.junit.jupiter.api.Test;
import org.leonidasanin.algorithmbasedsolvedtasks.model.OptimalSemiMagicSquare3By3Task;
import org.leonidasanin.algorithmbasedsolvedtasks.service.TaskService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class CalculateTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskService taskServiceMock;

    @Test
    void testNormalBehavior() throws Exception {
        //given
        var input = "1 2 3 4 5 6 7 8 9";
        var taskId = 1;
        var task = new OptimalSemiMagicSquare3By3Task();
        var result = task.solve(input);

        //when
        Mockito.when(taskServiceMock.getTaskById(taskId))
                .thenReturn(task);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/calculate")
                        .param("input", input)
                        .param("task", String.valueOf(taskId))
                )
        //then
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.flash().attribute("task", task))
                .andExpect(MockMvcResultMatchers.flash().attribute("input", input))
                .andExpect(MockMvcResultMatchers.flash().attribute("result", result))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    @Test
    void testExceptionThrowing() throws Exception {
        //given
        var input = "1 2 3 4 5 6 7 8 9 10";
        var task = new OptimalSemiMagicSquare3By3Task();
        var taskId = task.getId();

        //when
        Mockito.when(taskServiceMock.getTaskById(taskId))
                .thenReturn(task);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/calculate")
                        .param("input", input)
                        .param("task", String.valueOf(taskId))
                )
        //then
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.flash().attribute("task", task))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }
}
