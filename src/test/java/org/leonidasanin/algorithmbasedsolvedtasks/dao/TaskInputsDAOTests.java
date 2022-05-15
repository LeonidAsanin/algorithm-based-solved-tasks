package org.leonidasanin.algorithmbasedsolvedtasks.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import static org.junit.jupiter.api.Assertions.*;

class TaskInputsDAOTests {
    EmbeddedDatabase embeddedDataSource;
    JdbcTemplate jdbcTemplate;
    TaskInputsDAO taskInputsDAO;

    @BeforeEach
    void setup() {
        embeddedDataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .addScript("classpath:test-data.sql")
                .build();

        jdbcTemplate = new JdbcTemplate(embeddedDataSource);
        taskInputsDAO = new TaskInputsDAO(jdbcTemplate);
    }

    @AfterEach
    void tearDown() {
        embeddedDataSource.shutdown();
    }

    @Test
    void getInputsByTaskId() {
        //given
        var taskId = 1;

        //when
        var inputs = taskInputsDAO.getInputsByTaskId(taskId);

        //then
        assertAll(
                () -> assertEquals(1, inputs.get(0).getId()),
                () -> assertEquals(2, inputs.get(1).getId()),
                () -> assertEquals("1 2 3 4 5 6 7 8 9", inputs.get(0).getText()),
                () -> assertEquals("9 8 7 6 5 4 3 2 1", inputs.get(1).getText())
        );
    }

    @Test
    void getInputById() {
        //given
        var inputId = 4;

        //when
        var input = taskInputsDAO.getInputById(inputId);

        //then
        assertEquals("1, 22, 333; qqq, 11111, 22333", input);
    }

    @Test
    void save() {
        //given
        var taskId = 1;
        var input = "1 2 3 6 5 4 9 7 8";

        //when
        taskInputsDAO.save(taskId, input);

        //then
        assertEquals(input, taskInputsDAO.getInputById(5));
    }
}