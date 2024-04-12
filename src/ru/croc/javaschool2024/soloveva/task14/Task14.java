package ru.croc.javaschool2024.soloveva.task14;

import java.io.IOException;
import java.util.Map;

public class Task14 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Неверное количество аргументов!");
            return;
        }

        try {
            Map<String, Long> priceMap = DataReader.readFromFile(args[0]);
            Map<String, Long> estimateMap = DataReader.readFromFile(args[1]);

            System.out.println(TotalPriceCalculator.calculateTotalPrice(priceMap, estimateMap));
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}