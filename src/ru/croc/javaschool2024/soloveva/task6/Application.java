package ru.croc.javaschool2024.soloveva.task6;

/**
 * Приложение, проверяющее возможность прохождения последовательности клеток на шахматной доске ходом коня.
 */
public final class Application {
    public static void main(final String[] args) {
        try {
            KnightsMoveChecker checker = KnightsMoveCheckerFactory.get();
            checker.check(args);
            System.out.print("OK");
        } catch (IllegalMoveException e) {
            System.out.print("конь так не ходит: " + e.getMessage());
        }
    }
}

