package org.leonidasanin.algorithmbasedsolvedtasks.model;

public class Task {
    private int id;
    private String name;
    private String description;
    private String inputExample;

    public String solve(String input) {
        throw new UnsupportedOperationException();
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
}
