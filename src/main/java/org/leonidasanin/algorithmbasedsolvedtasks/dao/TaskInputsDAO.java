package org.leonidasanin.algorithmbasedsolvedtasks.dao;

import org.leonidasanin.algorithmbasedsolvedtasks.model.Input;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskInputsDAO {
    private final JdbcTemplate jdbcTemplate;

    public TaskInputsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Input> getInputsByTaskId(int taskId) {
        var sql = "SELECT id, input FROM taskInputs WHERE taskId = " + taskId;
        RowMapper<Input> rowMapper = (rs, rowNum) -> new Input(rs.getInt("id"),
                                                               rs.getString("input"));
        return jdbcTemplate.query(sql, rowMapper);
    }

    public String getInputById(int inputId) {
        var sql = "SELECT input FROM taskInputs WHERE id = " + inputId;
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("input");
        return jdbcTemplate.query(sql, rowMapper)
                .stream()
                .findFirst()
                .orElseThrow();
    }

    public void save(int taskId, String input) {
        if (taskId == 0) return;
        var sql = "INSERT INTO taskInputs(taskId, input) VALUES(" + taskId + ", '" + input + "')";
        jdbcTemplate.update(sql);
    }
}
