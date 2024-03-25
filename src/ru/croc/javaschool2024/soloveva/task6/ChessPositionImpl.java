package ru.croc.javaschool2024.soloveva.task6;

public class ChessPositionImpl implements ChessPosition {
    private int x;
    private int y;

    public ChessPositionImpl(int x, int y) throws IllegalPositionException {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new IllegalPositionException(x, y);
        }

        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public void setX(int x) throws IllegalPositionException {
        if (x < 0 || x > 7) {
            throw new IllegalPositionException(x, this.y);
        }

        this.x = x;
    }

    public int y() {
        return y;
    }

    public void setY(int y) throws IllegalPositionException {
        if (y < 0 || y > 7) {
            throw new IllegalPositionException(this.x, y);
        }

        this.y = y;
    }

    @Override
    public String toString() {
        char column = (char) ('a' + x);
        int row = y + 1;
        return String.valueOf(column) + row;
    }
}
