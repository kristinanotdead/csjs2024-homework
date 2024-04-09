package ru.croc.javaschool2024.soloveva.task11.models;

public class Movie {
    private int id;
    private String title;
    public Movie(int id, String title) {
        this.id = id;
        this.title = title;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
}