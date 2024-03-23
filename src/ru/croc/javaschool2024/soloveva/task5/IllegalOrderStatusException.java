package ru.croc.javaschool2024.soloveva.task5;

public class IllegalOrderStatusException extends Exception{
    @Override
    public String getMessage(){
        return "Заказ находится в неправильном статусе!";
    }
}
