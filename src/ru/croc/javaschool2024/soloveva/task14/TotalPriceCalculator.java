package ru.croc.javaschool2024.soloveva.task14;

import java.util.Map;
import java.util.stream.LongStream;

public class TotalPriceCalculator {
    public static long calculateTotalPrice(Map<String, Long> priceMap, Map<String, Long> estimateMap) {
        return estimateMap.entrySet().stream()
                .flatMapToLong(entry -> LongStream.of(priceMap.get(entry.getKey()) * entry.getValue()))
                .sum();
    }
}
