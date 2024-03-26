package org.example;

import org.example.entity.Ingredient;
import org.example.entity.Meal;

import java.sql.SQLOutput;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Menu {
    private List<Meal> meals;
    private final Scanner scanner;
    private final static Set<String> categories;

    static {
        categories = Stream.of("breakfast", "lunch", "dinner")
                .collect(Collectors.toSet());
    }

    Menu() {
        scanner = new Scanner(System.in);
        meals = new ArrayList<>();
    }
    public void run() {
        String command = "";
        while (!command.equals("exit")) {
            System.out.println(INPUT_COMMAND);
            command = scanner.nextLine();
            if ("add".equals(command)) {
                addNewMeal();
            } else if ("show".equals(command)) {
                showAllMeals();
            }
        }
        System.out.println(BYE);
    }

    private void addNewMeal() {
        String category = getCategoryFromInput();
        String name = getNameFromInput();
        List<Ingredient> ingredients = new ArrayList<>();
        for (String ingredientName : getIngredientsFromInput().split(",")) {
            ingredients.add(new Ingredient(ingredientName.trim()));
        }
        Meal meal = new Meal(category, name, ingredients);
        meals.add(meal);
        System.out.println(SUCCESS);
    }

    private String getCategoryFromInput() {
        System.out.println(INPUT_TYPE);
        String category = scanner.nextLine();
        while (!categories.contains(category)) {
            System.out.println(INPUT_WRONG_CATEGORY);
            category = scanner.nextLine();
        }
        return category;
    }

    private String getNameFromInput() {
        System.out.println(INPUT_NAME);
        String name = scanner.nextLine();
        while (!checkIfInputCorrect(name)) {
            System.out.println(INPUT_WRONG_FORMAT);
            name = scanner.nextLine();
        }
        return name;
    }

    private String getIngredientsFromInput() {
        System.out.println(INPUT_INGREDIENTS);
        String ingredients = scanner.nextLine();
        while (!checkIfInputCorrect(ingredients)) {
            System.out.println(INPUT_WRONG_FORMAT);
            ingredients = scanner.nextLine();
        }
        return ingredients;
    }

    private boolean checkIfInputCorrect(String input) {
        return Pattern.matches(PATTERN, input);
    }

    private void showAllMeals() {
        if (meals.isEmpty()) {
            System.out.println(OUTPUT_NO_MEALS);
        } else {
            System.out.println();
            for (Meal meal : meals) {
                System.out.println(meal);
                System.out.println();
            }
        }
    }

    private final String SUCCESS = "The meal has been added!";
    private final String INPUT_TYPE = "Which meal do you want to add (breakfast, lunch, dinner)?";
    private final String INPUT_NAME = "Input the meal's name:";
    private final String INPUT_INGREDIENTS = "Input the ingredients:";
    private final String INPUT_COMMAND = "What would you like to do (add, show, exit)?";
    private final String OUTPUT_NO_MEALS = "No meals saved. Add a meal first.";
    private final String INPUT_WRONG_CATEGORY = "Wrong meal category! Choose from: breakfast, lunch, dinner.";
    private final String INPUT_WRONG_FORMAT = "Wrong format. Use letters only!";
    private final String PATTERN = "\\s*[a-zA-Z]+(\\s*,?\\s*[a-zA-Z]+)*\\s*";

    private final String BYE = "Bye!";
}
