package ru.croc.javaschool2024.soloveva.task13.errors;

public class IncorrectAssessmentException extends RuntimeException{
    byte kingAssessment;
    byte courtiersAssessment;

    public IncorrectAssessmentException(byte kingAssessment, byte courtiersAssessment){
        super(generateErrorMessage(kingAssessment, courtiersAssessment));
        this.kingAssessment = kingAssessment;
        this.courtiersAssessment = courtiersAssessment;
    }

    private static String generateErrorMessage(byte kingAssessment, byte courtiersAssessment) {
        StringBuilder error = new StringBuilder();

        if(kingAssessment < 0 || kingAssessment > 100){
            error.append(String.format("Некорректная оценка короля: %d. Оценка должна быть от 0 до 100! ",
                    kingAssessment));
        }

        if(courtiersAssessment < 0 || courtiersAssessment > 100){
            error.append(String.format("Некорректная оценка придворных: %d. Оценка должна быть от 0 до 100! ",
                    courtiersAssessment));
        }

        return error.toString();
    }
}
