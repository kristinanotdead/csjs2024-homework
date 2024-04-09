package ru.croc.javaschool2024.soloveva.task12;

import java.util.function.Function;
import java.util.function.Predicate;

public class Task12 {
    public static <T, R> Function<T, R> ternaryOperator(
            Predicate<T> predicate,
            Function<T, R> ifTrue,
            Function<T, R> ifFalse) {
        return input -> predicate.test(input) ? ifTrue.apply(input) : ifFalse.apply(input);
    }

    public static void main(String[] args) {
        Predicate<Integer> isAdult = age -> age >= 18;
        Function<Integer, String> adultToString = age -> "Вы взрослый, вам уже исполнилось " + age;
        Function<Integer, String> childToString = age -> "Вы еще пупсик, вам пока что " + age;
        Function<Integer, String> childAdultOperator = ternaryOperator(
                isAdult,
                adultToString,
                childToString);

        System.out.println(childAdultOperator.apply(20));
        System.out.println(childAdultOperator.apply(17));

        Predicate<Integer> isEven = num -> num % 2 == 0;
        Function<Integer, String> evenToString = num -> "Even: " + num;
        Function<Integer, String> oddToString = num -> "Odd: " + num;
        Function<Integer, String> evenOddOperator = ternaryOperator(
                isEven,
                evenToString,
                oddToString);

        System.out.println(evenOddOperator.apply(10));
        System.out.println(evenOddOperator.apply(7));

        Predicate<Integer> isPositive = num -> num > 0;
        Function<Integer, String> positiveToString = num -> "Positive: " + num;
        Function<Integer, String> nonPositiveToString = num -> "Non-positive: " + num;
        Function<Integer, String> positiveOperator = ternaryOperator(
                isPositive,
                positiveToString,
                nonPositiveToString);

        System.out.println(positiveOperator.apply(5));
        System.out.println(positiveOperator.apply(-3));
        System.out.println(positiveOperator.apply(0));

        Predicate<String> isNumber = str -> {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        };
        Function<String, String> numberToString = str -> str + " is a number";
        Function<String, String> notNumberToString = str -> str + " is not a number";
        Function<String, String> numberOperator = ternaryOperator(
                isNumber,
                numberToString,
                notNumberToString);

        System.out.println(numberOperator.apply("3ab4"));
        System.out.println(numberOperator.apply("999"));
    }
}

