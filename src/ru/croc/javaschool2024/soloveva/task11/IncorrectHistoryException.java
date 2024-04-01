package ru.croc.javaschool2024.soloveva.task11;

public class IncorrectHistoryException extends Exception {
    private String line;

    public IncorrectHistoryException(String line) {
        super(String.format("Некорректная строка с историей просмотров: %s", line));
        this.line = line;
    }
}
