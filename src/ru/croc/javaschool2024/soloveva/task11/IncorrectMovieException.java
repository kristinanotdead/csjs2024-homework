package ru.croc.javaschool2024.soloveva.task11;

public class IncorrectMovieException extends Exception {
    private String line;

    public IncorrectMovieException(String line) {
        super(String.format("Некорректная строка с фильмом: %s", line));
        this.line = line;
    }
}
