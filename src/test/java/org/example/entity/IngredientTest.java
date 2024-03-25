package org.example.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    @Test
    void createEmptyIngredient() {
        Ingredient ingredient = new Ingredient();
        assertEquals(ingredient.getName(), null);
    }

    @Test
    void createIngredientWithName() {
        String name = "name";
        Ingredient ingredient = new Ingredient(name);
        assertEquals(ingredient.getName(), name);
    }
}
