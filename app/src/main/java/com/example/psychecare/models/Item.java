package com.example.psychecare.models;

public class Item {
    private int id;
    private String title; // Tiêu đề bài viết
    private String description; // Mô tả bài viết

    private String solution;

    public Item(int id, String title, String description, String solution) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.solution = solution;
    }

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
