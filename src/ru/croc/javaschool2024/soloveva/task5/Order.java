package ru.croc.javaschool2024.soloveva.task5;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class Order implements OrderStatusChanger{
    private final String orderNumber;
    private final LocalDateTime timeOfCreating;
    private LocalDateTime timeOfCollecting;
    private LocalDateTime timeOfClosing;
    private ArrayList<Product> orderList;
    private final String clientNameSurnPatr;
    private final String clientPhoneNumber;
    private OrderStatus status;
    private double totalSum;

    public OrderStatus getStatus() {
        return status;
    }

    public ArrayList<Product> getOrderList() {
        return orderList;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public LocalDateTime getTimeOfCreating() {
        return timeOfCreating;
    }

    public LocalDateTime getTimeOfCollecting() {
        return timeOfCollecting;
    }

    public LocalDateTime getTimeOfClosing() {
        return timeOfClosing;
    }

    public String getClientNameSurnPatr() {
        return clientNameSurnPatr;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void collectOrder() throws IllegalOrderStatusException {
        if (status != OrderStatus.CREATED){
            throw new IllegalOrderStatusException();
        }

        timeOfCollecting = LocalDateTime.now();
        status = OrderStatus.COLLECTED;
    }

    public void closeOrder() throws IllegalOrderStatusException {
        if (status != OrderStatus.COLLECTED){
            throw new IllegalOrderStatusException();
        }

        timeOfClosing = LocalDateTime.now();
        status = OrderStatus.CLOSED;
    }
    private double calculateTheTotalSum(ArrayList<Product> orderList) {
        totalSum = 0.0;
        for (Product product : orderList) {
            totalSum += product.getPrice();
        }
        return totalSum;
    }

    public Order(ArrayList<Product> orderList, String clientNameSurnPatr,
                 String clientPhoneNumber) throws IllegalOrderListException {
        if (!checkSizeOfOrder(orderList)) {
            throw new IllegalOrderListException();
        }

        this.orderList = orderList;
        this.timeOfCreating = LocalDateTime.now();
        this.orderNumber = generateOrderNumber(timeOfCreating, clientPhoneNumber);
        this.clientNameSurnPatr = clientNameSurnPatr;
        this.clientPhoneNumber = clientPhoneNumber;
        this.totalSum = calculateTheTotalSum(orderList);
        status = OrderStatus.CREATED;
    }

    private static String generateOrderNumber(LocalDateTime timeOfCreating, String clientPhoneNumber) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        String formattedOrderDateTime = timeOfCreating.format(formatter);
        String lastFourDigits = clientPhoneNumber.substring(clientPhoneNumber.length() - 4);
        String orderNumber = formattedOrderDateTime + lastFourDigits;
        return orderNumber;
    }

    private static boolean checkSizeOfOrder(ArrayList<Product> orderList) {
        return orderList.size() <= 10;
    }

    public String generateOrderConfirmationMessage() throws IllegalOrderStatusException{
        if (status != OrderStatus.CLOSED){
            throw new IllegalOrderStatusException();
        }

        StringBuilder document = new StringBuilder();

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy г.");

        document.append("Добрый день, ").append(clientNameSurnPatr).append("!\n\n");
        document.append("Рады сообщить, что Ваш заказ ").append(orderNumber).append(" собран и готов к выдаче.\n\n");
        document.append("Состав заказа:\n");

        for (Product product : orderList) {
            document.append(product.getName()).append(", ");
            document.append(decimalFormat.format(product.getPrice())).append("₽\n");
        }
        document.append("\n");
        document.append("Сумма к оплате: ").append(decimalFormat.format(totalSum)).append("₽\n\n");
        document.append("С наилучшими пожеланиями, магазин “Кошки и картошки”,\n");
        document.append(dateFormat.format(timeOfClosing));
        return document.toString();
    }
}

