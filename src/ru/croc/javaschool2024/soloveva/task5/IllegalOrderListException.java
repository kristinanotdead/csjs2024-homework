package ru.croc.javaschool2024.soloveva.task5;

public class IllegalOrderListException extends Exception{
    @Override
    public String getMessage(){
        return "Вы не можете добавить более 10 товаров в заказ!";
    }
}
