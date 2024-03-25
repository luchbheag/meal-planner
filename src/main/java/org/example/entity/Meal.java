package org.example.entity;

import java.util.ArrayList;
import java.util.List;

public class Meal {
    private final String CATEGORY = "Category: ";
    private final String NAME = "Name: ";
    private final String INGREDIENTS = "Ingredients:";
    private String category;
    private String name;
    private List<Ingredient> ingredients;

    public Meal() {}

    public Meal(String type, String name, List<Ingredient> ingredients) {
        this.category = type;
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CATEGORY);
        sb.append(category);
        sb.append('\n');
        sb.append(NAME);
        sb.append(name);
        sb.append('\n');
        sb.append(INGREDIENTS);
        for (Ingredient ingredient : ingredients) {
            sb.append('\n');
            sb.append(ingredient.getName());
        }
        return sb.toString();
    }
}
