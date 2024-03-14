package ru.croc.javaschool2024.soloveva.task3;

public class Task3 {
    public static long findSumOfArithmeticProgr(int start, int plus, int amount) {
        long sum = start;
        long el = start;

        for (int i = 0; i < amount - 1; i++) {
            el += plus;
            sum += el;
        }

        return sum;
    }

    public static void main(String[] args) {
        try {
            long sum = findSumOfArithmeticProgr(
                    Integer.parseInt(args[0]),
                    Integer.parseInt(args[1]),
                    Integer.parseInt(args[2])
            );

            System.out.println(sum);
        } catch (NumberFormatException ex) {
            System.out.println("Неправильный формат ввода");
        } catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Нет входных данных");
        }
    }
}
