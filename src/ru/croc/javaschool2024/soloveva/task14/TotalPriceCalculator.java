package ru.croc.javaschool2024.soloveva.task14;

import java.math.BigDecimal;
import java.util.Map;

public class TotalPriceCalculator {
    public static BigDecimal calculateTotalPrice(Map<String, Long> priceMap, Map<String, Long> estimateMap) {
        return BigDecimal.valueOf(estimateMap.entrySet().stream()
                .mapToLong(entry -> priceMap.get(entry.getKey()) * entry.getValue())
                .sum());
    }
}
