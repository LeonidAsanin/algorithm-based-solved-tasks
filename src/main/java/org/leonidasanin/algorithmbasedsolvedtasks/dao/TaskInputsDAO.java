package org.leonidasanin.algorithmbasedsolvedtasks.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskInputsDAO {
    public List<String> getInputsByTaskId(int taskId) {
        if (taskId == 1) {
            return List.of();
        } else if (taskId == 2){
            return List.of("input21", "input22", "input23");
        } else {
            return List.of();
        }
    }

    public void save(int taskId, String input) {
        if (taskId == 0) return;
        System.out.println("saved: " + taskId + " with " + input);
    }
}
