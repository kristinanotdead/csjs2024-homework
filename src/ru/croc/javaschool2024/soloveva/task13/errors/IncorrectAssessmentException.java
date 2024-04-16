package ru.croc.javaschool2024.soloveva.task13.errors;

public class IncorrectAssessmentException extends RuntimeException {
    private byte kingAssessment;
    private byte courtiersAssessment;

    public IncorrectAssessmentException(byte kingAssessment, byte courtiersAssessment) {
        super(generateErrorMessage(kingAssessment, courtiersAssessment));
        this.kingAssessment = kingAssessment;
        this.courtiersAssessment = courtiersAssessment;
    }

    /* Метод статический, потому что мы должны сгенерировать сообщение об ошибке и передать его в конструктор
     * базового класса до создания объекта IncorrectAssessmentException
     */
    private static String generateErrorMessage(byte kingAssessment, byte courtiersAssessment) {
        StringBuilder error = new StringBuilder();

        if (kingAssessment < 0 || kingAssessment > 100) {
            error.append(String.format("Некорректная оценка короля: %d. Оценка должна быть от 0 до 100! ",
                    kingAssessment));
        }

        if (courtiersAssessment < 0 || courtiersAssessment > 100) {
            error.append(String.format("Некорректная оценка придворных: %d. Оценка должна быть от 0 до 100! ",
                    courtiersAssessment));
        }

        return error.toString();
    }
}
