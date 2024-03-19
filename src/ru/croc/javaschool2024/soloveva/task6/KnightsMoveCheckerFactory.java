package ru.croc.javaschool2024.soloveva.task6;
/**
 * Класс, реализующий фабричный метод, возвращающий обработчики, проверяющие, что последовательность клеток на шахматной
 * доске может быть пройдена ходом коня.
 *
 * @author Dmitry Malenok
 */

public final class KnightsMoveCheckerFactory {

    /**
     * Конструктор.
     */
    private KnightsMoveCheckerFactory() {
        // Конструктор задан только для того, чтобы экземпляр класса случайно не создали.
    }

    /**
     * Возвращает обработчик, проверяющий, что последовательность клеток на шахматной доске может быть пройдена ходом
     * коня.
     *
     * @return обработчик, проверяющий, что последовательность клеток на шахматной доске может быть пройдена ходом коня
     */
    public static KnightsMoveChecker get() {
        return positions -> {
            for (int i = 0; i < positions.length - 1; i++) {
                ChessPosition from = ChessPositionParser.parse(positions[i]);
                ChessPosition to = ChessPositionParser.parse(positions[i + 1]);

                int deltaX = Math.abs(from.x() - to.x());
                int deltaY = Math.abs(from.y() - to.y());

                if(!((deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1)))
                {
                    throw new IllegalMoveException(from, to);
                }
            }
        };
    }
}