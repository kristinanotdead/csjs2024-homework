package ru.croc.javaschool2024.soloveva.task11.models;

import java.util.HashMap;

public class User {
    private int id;
    private HashMap<Integer, Integer> views;
    public User(int id) {
        this.id = id;
        this.views = new HashMap<>();
    }
    public int getId() {
        return id;
    }
    public void addView(int movieId) {
        int viewsCount = views.getOrDefault(movieId, 0);
        views.put(movieId, viewsCount + 1);
    }

    public HashMap<Integer, Integer> getViews() {
        return views;
    }
}

