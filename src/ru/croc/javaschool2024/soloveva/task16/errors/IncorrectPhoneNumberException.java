package ru.croc.javaschool2024.soloveva.task16.errors;

public class IncorrectPhoneNumberException extends Exception{
    private String phoneNumber;

    public IncorrectPhoneNumberException(String phoneNumber){
        super(generateErrorMessage(phoneNumber));
        this.phoneNumber = phoneNumber;
    }

    private static String generateErrorMessage(String phoneNumber) {
        return String.format("Клиент с данным номером уже есть в базе: %s", phoneNumber);
    }
}
