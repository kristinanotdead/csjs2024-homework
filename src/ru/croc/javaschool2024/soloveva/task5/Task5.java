package ru.croc.javaschool2024.soloveva.task5;

import java.util.ArrayList;

public class Task5 {
    public static void main(String[] args) {
        Product item1 = new Product("01", "Плюшевый мишка", 1000.00,
                "Милый подарок на 14 ферваля");
        Product item2 = new Product("02", "MacBook", 100000.00,
                "Неотъемлемая часть жизни каждого программиста-путешественника");
        Product item3 = new Product("03", "Наушники", 10000.00,
                "Беспроводные наушники с активным шумоподавлением");
        Product item4 = new Product("04", "Фотоаппарат", 50000.00,
                "Верный спутник в ваших путешествиях");
        Product item5 = new Product("05", "Компас", 1000.00,
                "Не позволит вам потеряться в лесу");
        Product item6 = new Product("06", "Термос", 500.00,
                "Всегда поможет согреться");
        Product item7 = new Product("07", "Шапка", 2000.00,
                "Не даст вашей умной голове замерзнуть");
        Product item8 = new Product("08", "Смартфон", 50000.00,
                "С ним вы всегда будете на связи");
        Product item9 = new Product("09", "Сникерс", 100.00,
                "Быстрые углеводы");
        Product item10 = new Product("10", "Велосипед", 50000.00,
                "Отличный подарок программисту");
        Product item11 = new Product("11", "Доширак", 25.00,
                "С ним вы не умрете с голоду в общаге");

        ArrayList<Product> orderList = new ArrayList<>();
        orderList.add(item1);
        orderList.add(item2);
        orderList.add(item3);
        orderList.add(item4);
        orderList.add(item5);
        orderList.add(item6);
        orderList.add(item7);
        orderList.add(item8);
        orderList.add(item9);
        orderList.add(item10);
        orderList.add(item11);

        System.out.printf("Создаём первый заказ из %d товаров\n", orderList.size());

        Order order = null;

        try {
            order = new Order(orderList, "Соловьева Кристина",
                    "+79021572525");
        } catch (IllegalOrderListException e) {
            System.out.println(e.getMessage());
        }

        System.out.printf("Удаляем товар %s\n", orderList.remove(10).getName());

        System.out.printf("Создаём второй заказ из %d товаров\n", orderList.size());

        try {
            order = new Order(orderList, "Соловьева Кристина",
                    "+79021572525");
        } catch (IllegalOrderListException e) {
            System.out.println("Ошибка при создании заказа!");
        }

        try {
            order.collectOrder();
            order.collectOrder();
        } catch (NullPointerException | IllegalOrderStatusException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(order.generateOrderConfirmationMessage());
        } catch (NullPointerException | IllegalOrderStatusException e) {
            System.out.println(e.getMessage());
        }

        try {
            order.closeOrder();
            System.out.println(order.generateOrderConfirmationMessage());
        } catch (NullPointerException | IllegalOrderStatusException e) {
            System.out.println(e.getMessage());
        }
    }

}






