package com.example.juyoung.boostcamp.model;

import java.util.List;

public class Message {
    private int total;
    private List<Movie> items;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Movie> getItems() {
        return items;
    }

    public void setItems(List<Movie> items) {
        this.items = items;
    }
}
