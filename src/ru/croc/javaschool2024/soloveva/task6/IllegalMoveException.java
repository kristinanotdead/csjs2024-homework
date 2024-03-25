package ru.croc.javaschool2024.soloveva.task6;

/**
 * Исключение, выбрасываемое в случае, если при перемещении шахматного коня из текущей клетки в следующую происходит с
 * нарушением правил.
 * 
 * @author Dmitry Malenok
 */
public class IllegalMoveException extends Exception {
    private ChessPosition from;
    private ChessPosition to;

    public IllegalMoveException(ChessPosition from, ChessPosition to) {
        super(String.format("%s -> %s", from, to));
        this.from = from;
        this.to = to;
    }

    public ChessPosition getFrom() {
        return from;
    }

    public ChessPosition getTo() {
        return to;
    }
}

