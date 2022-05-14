package org.leonidasanin.algorithmbasedsolvedtasks.model;

import java.util.Objects;

public class Input {
    private int id;
    private String text;

    public Input(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public Input setId(int id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public Input setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Input input = (Input) o;

        return Objects.equals(text, input.text);
    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }
}
