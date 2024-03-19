package ru.croc.javaschool2024.soloveva.task6;

/**
 * Расположение фигуры на традиционной шахматной доске 8x8.
 *
 * @author Dmitry Malenok
 */
public class ChessPosition {
    private int x;
    private int y;

    public ChessPosition(int x, int y) throws IllegalPositionException {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new IllegalPositionException(x, y);
        }

        this.x = x;
        this.y = y;
    }

    /**
     * Возвращает позицию фигуры по горизонтали.
     * <p/>
     * Возможные значения: 0 - 7.
     *
     * @return позицию фигуры по горизонтали
     */
    public int x() {
        return x;
    }

    public void setX(int x) throws IllegalPositionException {
        if (x < 0 || x > 7) {
            throw new IllegalPositionException(x, this.y);
        }

        this.x = x;
    }

    /**
     * Возвращает позицию фигуры по вертикали.
     * <p/>
     * Возможные значения: 0 - 7.
     * @return позицию фигуры по вертикали
     */
    public int y() {
        return y;
    }

    public void setY(int y) throws IllegalPositionException {
        if (y < 0 || y > 7) {
            throw new IllegalPositionException(this.x, y);
        }

        this.y = y;
    }

    /**
     * Возвращает наименование клетки шахматной доски, на которой находится фигура, в
     * <a href="https://w.wiki/7pFN">шахматной нотации</a>.
     *
     * @return наименование клетки шахматной доски, на которой находится фигура, в шахматной нотации
     */
    @Override
    public String toString() {
        char column = (char) ('a' + x);
        int row = y + 1;
        return String.valueOf(column) + row;
    }
}
