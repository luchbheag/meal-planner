package org.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "meals")
public class Meal {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "category")
    private String category;
    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "meal_ingredient",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    public Meal() {}

    public Meal(String category, String name) {
        this.category = category;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void addIngredientToMeal(Ingredient ingredient) {
        if (ingredients == null) {
            ingredients = new ArrayList<>();
        }
        ingredients.add(ingredient);
    }

    @Override
    public String toString() {
        return CATEGORY + category + '\n' + this.toStringWithoutCategory();
    }

    public String toStringWithoutCategory() {
        StringBuilder sb = new StringBuilder();
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

    private static final String CATEGORY = "Category: ";
    private static final String NAME = "Name: ";
    private static final String INGREDIENTS = "Ingredients:";
}
