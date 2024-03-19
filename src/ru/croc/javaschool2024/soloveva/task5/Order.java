package ru.croc.javaschool2024.soloveva.task5;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Order {
    private final String orderNumber;
    private final LocalDateTime timeOfCreating;
    private LocalDateTime timeOfCollecting;
    private LocalDateTime timeOfGetting;
    public final ArrayList<Product> orderList;
    private final String clientNameSurnPatr;
    private final String clientPhoneNumber;
    private OrderStatus status;
    private double totalSum;

    public void collectOrder() {
        timeOfCollecting = LocalDateTime.now();
        status = OrderStatus.COLLECTED;
    }

    public void giveOrder() {
        timeOfGetting = LocalDateTime.now();
        status = OrderStatus.CLOSED;
        System.out.println(generateOrderConfirmationMessage());
    }
    private double calculateTheTotalSum(ArrayList<Product> orderList) {
        totalSum = 0.0;
        for (Product product : orderList) {
            totalSum += product.getPrice();
        }
        return totalSum;
    }

    public static Order createOrder(ArrayList<Product> orderList, String clientNameSurnPatr, String clientPhoneNumber) {
        if (!checkSizeOfOrder(orderList)) {
            System.out.println("Вы не можете добавить более 10 товаров в заказ!");
            return null;
        }
        LocalDateTime timeOfCreating = LocalDateTime.now();
        return new Order(generateOrderNumber(timeOfCreating, clientPhoneNumber), timeOfCreating, orderList, clientNameSurnPatr, clientPhoneNumber);
    }

    private Order(String orderNumber, LocalDateTime timeOfCreating, ArrayList<Product> orderList, String clientNameSurnPatr, String clientPhoneNumber) {
        this.orderList = orderList;
        this.timeOfCreating = timeOfCreating;
        this.orderNumber = orderNumber;
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

    public String generateOrderConfirmationMessage() {
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
        document.append(dateFormat.format(timeOfGetting));
        return document.toString();
    }
}

