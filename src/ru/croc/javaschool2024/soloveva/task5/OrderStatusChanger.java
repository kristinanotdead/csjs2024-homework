package ru.croc.javaschool2024.soloveva.task5;

public interface OrderStatusChanger {
    void collectOrder() throws IllegalOrderStatusException;
    void closeOrder() throws IllegalOrderStatusException;
}
