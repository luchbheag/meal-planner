package org.example.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class MealTest {
    @Test
    void createEmptyMeal() {
        Meal meal = new Meal();
        assertAll("Assertions for empty Meal",
                () -> assertEquals(meal.getCategory(), null),
                () -> assertEquals(meal.getName(), null),
                () -> assertEquals(meal.getIngredients(), null));
    }

    @Test
    void createMealWithNonEmptyFields() {
        String category = "lunch";
        String name = "soup";
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("water"));
        ingredients.add(new Ingredient("soup"));
        Meal meal = new Meal(category, name, ingredients);
        assertAll("Assertions for non-empty Meal",
                () -> assertEquals(meal.getCategory(), category),
                () -> assertEquals(meal.getName(), name),
                () -> assertEquals(meal.getIngredients(), ingredients));
    }
}
