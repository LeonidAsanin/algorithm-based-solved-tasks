package org.leonidasanin.algorithmbasedsolvedtasks.model;

import org.springframework.stereotype.Component;

@Component
public class Task1 extends Task {
    {
        setId(1);
        setName("task1");
        setDescription("desc1");
        setInputExample("inputEx1");
    }

    @Override
    public String solve(String input) {
        return "result1";
    }
}
