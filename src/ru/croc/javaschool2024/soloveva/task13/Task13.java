package ru.croc.javaschool2024.soloveva.task13;

import ru.croc.javaschool2024.soloveva.task13.dish.Dish;
import ru.croc.javaschool2024.soloveva.task13.dish.DishCategory;
import ru.croc.javaschool2024.soloveva.task13.errors.IncorrectAssessmentException;
import ru.croc.javaschool2024.soloveva.task13.kitchener.Kitchener;

import java.util.*;

public class Task13 {
    public static void main(String[] args) {
        Kitchener Kristina = new Kitchener(1, "Кристина");
        Kitchener Natalya = new Kitchener(2, "Наталья");

        Dish shrimpPasta = new Dish(
                "Паста с креветками",
                "-",
                List.of("макароны", "сливки", "соль", "креветки", "сыр", "томаты", "брокколи"),
                DishCategory.MAIN_DISH,
                (byte) 99,
                (byte) 91
        );

        Dish tomYam = new Dish(
                "Том Ям",
                "-",
                List.of("рис", "кокосовое молоко", "паста том ям", "креветки", "кальмары", "мидии"),
                DishCategory.MAIN_DISH,
                (byte) 85,
                (byte) 80
        );

        Dish pelmeni = new Dish(
                "Пельмени",
                "-",
                List.of("тесто", "мясо", "сметана"),
                DishCategory.MAIN_DISH,
                (byte) 80,
                (byte) 77
        );

        Dish poke = new Dish(
                "Поке",
                "-",
                List.of("рис", "тунец", "соус", "лист нори"),
                DishCategory.MAIN_DISH,
                (byte) 95,
                (byte) 92
        );

        Dish medovik = new Dish(
                "Медовик",
                "-",
                List.of("коржи", "сливочный крем", "посыпка", "мёд"),
                DishCategory.MAIN_DISH,
                (byte) 90,
                (byte) 85
        );

        HashMap<Kitchener, Set<Dish>> kitchenerDishesMap = new HashMap<>() {
            {
                put(Kristina, Set.of(tomYam, pelmeni, shrimpPasta));
                put(Natalya, Set.of(medovik, poke));
            }
        };

        try {
            Dish incorrectDish = new Dish(
                    "Чертополошинка",
                    "-",
                    List.of("мухомор", "зверобой", "хвост дракона"),
                    DishCategory.MAIN_DISH,
                    (byte) -7,
                    (byte) 111
            );
        } catch (IncorrectAssessmentException e){
            System.out.println(e);
        }

        Kitchen kitchen = new Kitchen(kitchenerDishesMap);

        Collection<Dish> menu = kitchen.generateMenu(List.of(Kristina, Natalya), List.of("рис"), 2);

        if(!menu.isEmpty()) {
            System.out.println("Меню:");

            for (Dish dish : menu) {
                System.out.println(dish.getName());
            }
        }

        Collection<Dish> additionalMenu = kitchen.generateMenu(
                List.of(Kristina, Natalya),
                List.of("рис"),
                (dish) -> dish.getIngredients().contains("мёд"),
                2);

        System.out.println();

        if(!additionalMenu.isEmpty()) {
            System.out.println("Особое меню:");

            for (Dish dish : additionalMenu) {
                System.out.println(dish.getName());
            }
        }
    }
}
