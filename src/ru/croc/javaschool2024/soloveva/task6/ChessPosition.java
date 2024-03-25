package ru.croc.javaschool2024.soloveva.task6;

/**
 * Расположение фигуры на традиционной шахматной доске 8x8.
 * 
 * @author Dmitry Malenok
 */
public interface ChessPosition {

    /**
     * Возвращает позицию фигуры по горизонтали.
     * <p/>
     * Возможные значения: 0 - 7.
     * 
     * @return позицию фигуры по горизонтали
     */
    int x();

    /**
     * Возвращает позицию фигуры по вертикали.
     * <p/>
     * Возможные значения: 0 - 7.
     * 
     * @return позицию фигуры по вертикали
     */
    int y();

    /**
     * Возвращает наименование клетки шахматной доски, на которой находится фигура, в
     * <a href="https://w.wiki/7pFN">шахматной нотации</a>.
     * 
     * @return наименование клетки шахматной доски, на которой находится фигура, в шахматной нотации
     */
    @Override
    String toString();
}
