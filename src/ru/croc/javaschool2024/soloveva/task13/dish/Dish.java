package ru.croc.javaschool2024.soloveva.task13.dish;

import ru.croc.javaschool2024.soloveva.task13.errors.IncorrectAssessmentException;

import java.util.*;

public class Dish {
    private String name;
    private String recipe;
    private Set<String> ingredients;
    private DishCategory category;
    private byte kingAssessment;
    private byte courtiersAssessment;

    public Dish(
            String name,
            String recipe,
            Collection<String> ingredients,
            DishCategory category,
            byte kingAssessment,
            byte courtiersAssessment
    ) {
        if(kingAssessment < 0 || kingAssessment > 100
                || courtiersAssessment < 0 || courtiersAssessment > 100) {
            throw new IncorrectAssessmentException(kingAssessment, courtiersAssessment);
        }

        this.name = name;
        this.recipe = recipe;
        this.category = category;
        this.kingAssessment = kingAssessment;
        this.courtiersAssessment = courtiersAssessment;
        this.ingredients = new HashSet<>(ingredients);
    }

    public DishCategory getCategory() {
        return category;
    }

    public Collection<String> getIngredients() {
        return ingredients;
    }

    public String getName() {
        return name;
    }

    public byte getKingAssessment() {
        return kingAssessment;
    }

    public byte getCourtiersAssessment() {
        return courtiersAssessment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return getKingAssessment() == dish.getKingAssessment()
                && getCourtiersAssessment() == dish.getCourtiersAssessment()
                && Objects.equals(getName(), dish.getName())
                && Objects.equals(recipe, dish.recipe)
                && Objects.equals(getIngredients(), dish.getIngredients())
                && getCategory() == dish.getCategory();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), recipe, getIngredients(), getCategory(),
                getKingAssessment(), getCourtiersAssessment());
    }
}
