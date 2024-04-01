package ru.croc.javaschool2024.soloveva.task10;

import java.util.*;

public class Task10 {
    public static void main(String[] args) {
        BlackListFilter filter = new BlackListFilterImpl();
        List<String> comments = new ArrayList<>(Arrays.asList("Лучший рецепт!", "Фу, какой мрак", "Чучело!"));
        Set<String> blacklist = Set.of("мрак", "чучело");

        filter.filterComments(comments, blacklist);

        System.out.println(comments);
    }
}
