package org.example;

import org.example.entity.Ingredient;
import org.example.entity.Meal;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final String SUCCESS = "The meal has been added!";
    private final String INPUT_TYPE = "Which meal do you want to add (breakfast, lunch, dinner)?";
    private final String INPUT_NAME = "Input the meal's name:";
    private final String INPUT_INGREDIENTS = "Input the ingredients:";

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(INPUT_TYPE);
        String category = scanner.nextLine();
        System.out.println(INPUT_NAME);
        String name = scanner.nextLine();
        System.out.println(INPUT_INGREDIENTS);
        List<Ingredient> ingredients = new ArrayList<>();
        for (String ingredientName : scanner.nextLine().split(",")) {
            ingredients.add(new Ingredient(ingredientName));
        }
        Meal meal = new Meal(category, name, ingredients);
        System.out.println();
        System.out.println(meal);
        System.out.println(SUCCESS);
    }
}
