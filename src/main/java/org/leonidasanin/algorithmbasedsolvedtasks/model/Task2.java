package org.leonidasanin.algorithmbasedsolvedtasks.model;

import org.springframework.stereotype.Component;

@Component
public class Task2 extends Task {
    {
        setId(2);
        setName("task2");
        setDescription("desc2");
        setInputExample("inputEx2");
    }

    @Override
    public String solve(String input) {
        return "result2";
    }
}
