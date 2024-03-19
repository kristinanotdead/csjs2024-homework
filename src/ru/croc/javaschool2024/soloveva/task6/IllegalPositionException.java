package ru.croc.javaschool2024.soloveva.task6;

public class IllegalPositionException extends RuntimeException {
    private int x;
    private int y;
    private String position;

    public IllegalPositionException(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public IllegalPositionException(String position) {
        this.position = position;
    }

    @Override
    public String getMessage() {
        if(!position.isEmpty()){
            return String.format("Неверные данные для ввода координаты: %s", position);
        }

        return String.format("Координаты со строкой %d и столбцом %d не существует", x, y);
    }
}
