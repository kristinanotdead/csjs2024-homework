package ru.croc.javaschool2024.soloveva.task3;

public class Task3 {
    public static void findSumOfArithmeticProgr(int start, int plus, int amount) {
        int sum = start;
        int el = start;

        for (int i = 0; i < amount - 1; i++) {
            el += plus;
            sum += el;
        }

        System.out.println(sum);
    }

    public static void main(String[] args) {
        try {
            findSumOfArithmeticProgr(
                    Integer.parseInt(args[0]),
                    Integer.parseInt(args[1]),
                    Integer.parseInt(args[2])
            );
        } catch (NumberFormatException ex) {
            System.out.println("Неправильный формат ввода");
        }
    }
}
