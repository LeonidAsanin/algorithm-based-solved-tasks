package org.leonidasanin.algorithmbasedsolvedtasks.model;

import org.leonidasanin.algorithmbasedsolvedtasks.exception.TaskException;

public class Task {
    private int id;
    private String name;
    private String description;
    private String inputExample;

    public String solve(String input) throws TaskException {
        if (!isInputCorrect(input)) throw new TaskException("Incorrect Input", this);
        return "";
    }

    public boolean isInputCorrect(String input) {
        return false;
    }

    public int getId() {
        return id;
    }

    public Task setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Task setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Task setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getInputExample() {
        return inputExample;
    }

    public Task setInputExample(String inputExample) {
        this.inputExample = inputExample;
        return this;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", inputExample='" + inputExample + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        return id == task.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
