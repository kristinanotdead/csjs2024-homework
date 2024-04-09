package ru.croc.javaschool2024.soloveva.task13;

import ru.croc.javaschool2024.soloveva.task13.dish.Dish;
import ru.croc.javaschool2024.soloveva.task13.kitchener.Kitchener;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Kitchen {
    private final Map<Kitchener, Set<Dish>> kitchenerDishesMap;

    public Kitchen(Map<Kitchener, Set<Dish>> kitchenerDishesMap) {
        this.kitchenerDishesMap = kitchenerDishesMap;
    }

    private Collection<Dish> generateMenu(
            Collection<Kitchener> workingKitcheners,
            Collection<String> unavailableIngredients
    ) {
        return kitchenerDishesMap
                .entrySet().stream()
                .filter(entry -> workingKitcheners.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                .values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet()).stream()
                .filter(dish -> dish.getIngredients().stream().noneMatch(unavailableIngredients::contains))
                .sorted(Comparator.comparingInt(Dish::getKingAssessment)
                        .thenComparing(Dish::getCourtiersAssessment).reversed())
                .toList();
    }

    /**
     * Метод генерации меню.
     *
     * @param workingKitcheners      Список сотрудников, работающих в нужный день
     * @param unavailableIngredients Недоступные (по причине отсутствия их на кухне) ингредиенты
     * @param maxLength              Максимально допустимая длина генерируемого меню
     * @return Перечень блюд, которые возможно приготовить, отсортированный по оценкам, не длиннее maxLength
     */
    public Collection<Dish> generateMenu(
        Collection<Kitchener> workingKitcheners,
        Collection<String> unavailableIngredients,
        int maxLength
    ) {
        return generateMenu(workingKitcheners, unavailableIngredients).stream()
                .limit(maxLength).toList();
    }

    /**
     * Метод генерации меню с учётом дополнительный требований.
     *
     * @param workingKitcheners      Список сотрудников, работающих в нужный день
     * @param unavailableIngredients Недоступные (по причине отсутствия их на кухне) ингредиенты
     * @param additionalConditions   Дополнительные требования
     * @param maxLength              Максимально допустимая длина генерируемого меню
     * @return Перечень блюд, которые возможно приготовить, отсортированный по оценкам, не длиннее maxLength
     */
    public Collection<Dish> generateMenu(
        Collection<Kitchener> workingKitcheners,
        Collection<String> unavailableIngredients,
        Predicate<Dish> additionalConditions,
        int maxLength
    ) {
        return generateMenu(workingKitcheners, unavailableIngredients).stream()
                .filter(additionalConditions).limit(maxLength).toList();
    }
}
