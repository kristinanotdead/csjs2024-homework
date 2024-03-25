package ru.croc.javaschool2024.soloveva.task6;

/**
 * Класс, содержащий методы преобразования в объект расположения фигуры на шахматной доске из различных форматов.
 * 
 * @author Dmitry Malenok
 * @see ChessPosition
 */
public final class ChessPositionParser {
    private ChessPositionParser() {
        // Конструктор задан только для того, чтобы экземпляр класса случайно не создали.
    }
    /**
     * Разбирает наименование клетки шахматной доски, на которой находится фигура, в
     * <a href="https://w.wiki/7pFN">шахматной нотации</a> и возвращает соответствующий ей объект расположения фигуры на
     * шахматной доске.
     *
     * @param position наименование клетки шахматной доски, на которой находится фигура
     * @return объект расположения фигуры на шахматной доске, соответствующий переданному наименованию клетки
     */
    public static ChessPosition parse(final String position) {
        if (position.length() != 2) {
            throw new IllegalPositionException(position);
        }

        char columnChar = position.charAt(0);
        char rowChar = position.charAt(1);
        int x = columnChar - 'a';
        int y = rowChar - '1';

        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new IllegalPositionException(position, x, y);
        }

        return new ChessPositionImpl(x, y);
    }
}
