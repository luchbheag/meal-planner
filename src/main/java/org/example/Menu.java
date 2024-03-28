package org.example;

import org.example.entity.Ingredient;
import org.example.entity.Meal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Menu {
    private SessionFactory factory;
    private final Scanner scanner;
    private final static Set<String> categories;

    static {
        categories = Stream.of("breakfast", "lunch", "dinner")
                .collect(Collectors.toSet());
    }

    Menu() {
        scanner = new Scanner(System.in);
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Meal.class)
                .addAnnotatedClass(Ingredient.class)
                .buildSessionFactory();
    }
    public void run() {
        String command = "";
        while (!command.equals("exit")) {
            System.out.println(INPUT_COMMAND);
            command = scanner.nextLine();
            if ("add".equals(command)) {
                addNewMeal();
            } else if ("show".equals(command)) {
                showAllMealsOfCategory();
            }
        }
        factory.close();
        System.out.println(BYE);
    }

    private void addNewMeal() {
        Session session = null;
        try {
            String category = getCategoryFromInput(INPUT_CATEGORY);
            String name = getNameFromInput();
            Meal meal = new Meal(category, name);
            for (String ingredientName : getIngredientsFromInput().split(",")) {
                Ingredient ingredient = findIngredientByNameOrCreateNewOne(ingredientName.trim());
                meal.addIngredientToMeal(ingredient);
            }

            session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(meal);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println(SUCCESS);
    }

    private String getCategoryFromInput(final String question) {
        System.out.println(question);
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

    private Ingredient findIngredientByNameOrCreateNewOne(String name) {
        Session session = null;
        Ingredient ingredient = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            List<Ingredient> list = session
                    .createQuery("from Ingredient where name=:name")
                    .setParameter("name", name)
                    .getResultList();
            session.getTransaction().commit();
            ingredient = !list.isEmpty() ? list.get(0) : new Ingredient(name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ingredient;
    }

    private void showAllMealsOfCategory() {
        String category = getCategoryFromInput(INPUT_CHOOSE_CATEGORY);
        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            List<Meal> meals = session
                    .createQuery("from Meal where category=:category")
                    .setParameter("category", category)
                    .getResultList();
            session.getTransaction().commit();
            printAllMealsFromList(meals, category);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    private void printAllMealsFromList(List<Meal> meals, String category) {
        if (meals.isEmpty()) {
            System.out.println(OUTPUT_NO_MEALS);
        } else {
            System.out.println(String.format("%s%s\n", CATEGORY, category));
            for (Meal meal : meals) {
                System.out.println(meal.toStringWithoutCategory());
                System.out.println();
            }
        }
    }

    private static final String CATEGORY = "Category: ";
    private static final String SUCCESS = "The meal has been added!";
    private static final String INPUT_CATEGORY = "Which meal do you want to add (breakfast, lunch, dinner)?";
    private static final String INPUT_NAME = "Input the meal's name:";
    private static final String INPUT_INGREDIENTS = "Input the ingredients:";
    private static final String INPUT_COMMAND = "What would you like to do (add, show, exit)?";
    private static final String OUTPUT_NO_MEALS = "No meals saved. Add a meal first.";
    private static final String INPUT_WRONG_CATEGORY = "Wrong meal category! Choose from: breakfast, lunch, dinner.";
    private static final String INPUT_WRONG_FORMAT = "Wrong format. Use letters only!";
    private static final String INPUT_CHOOSE_CATEGORY = "Which category do you want to print (breakfast, lunch, dinner)?";
    private static final String PATTERN = "\\s*[a-zA-Z]+(\\s*,?\\s*[a-zA-Z]+)*\\s*";
    private static final String BYE = "Bye!";
}
