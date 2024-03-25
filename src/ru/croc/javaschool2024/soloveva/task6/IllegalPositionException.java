package ru.croc.javaschool2024.soloveva.task6;

public class IllegalPositionException extends RuntimeException {
    private int x;
    private int y;
    private String position;

    public IllegalPositionException(int x, int y) {
        super(generateErrorMessage(x, y));
        this.x = x;
        this.y = y;
    }

    public IllegalPositionException(String position) {
        super(String.format("Длина строки с описанием координаты превышает 2 символа: %s", position));
        this.position = position;
    }

    public IllegalPositionException(String position, int x, int y) {
        super(String.format("Введена координата: %s. %s", position, generateErrorMessage(x, y)));
        this.x = x;
        this.y = y;
        this.position = position;
    }

    private static String generateErrorMessage(int x, int y) {
        StringBuilder error = new StringBuilder();

        if(x < 0 || x > 7){
            error.append(String.format("Столбец '%s' не принадлежит диапазону от a до h! ", (char)('a' + x)));
        }

        if(y < 0 || y > 7){
            error.append(String.format("Строка %s не принадлежит диапазону от 1 до 8!", (char)('1' + y)));
        }

        return error.toString();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getPosition() {
        return position;
    }
}
