package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {

    public static final int MEAL_ID_1 = START_SEQ + 2;
    public static final int MEAL_ID_2 = START_SEQ + 3;
    public static final int MEAL_ID_3 = START_SEQ + 4;
    public static final int MEAL_ID_4 = START_SEQ + 5;
    public static final int MEAL_ID_5 = START_SEQ + 6;
    public static final int MEAL_ID_6 = START_SEQ + 7;

    public static final LocalDateTime DATE_1 = LocalDateTime.of(2017, 4, 10, 0,0, 0);
    public static final LocalDateTime DATE_2 = LocalDateTime.of(2017, 5, 11, 0,0, 0);

    public static final Meal MEAL_1 = new Meal(MEAL_ID_1, DATE_1.plusHours(9), "Breakfast", 500);
    public static final Meal MEAL_2 = new Meal(MEAL_ID_2, DATE_1.plusHours(14), "Lunch", 2000);
    public static final Meal MEAL_3 = new Meal(MEAL_ID_3, DATE_1.plusHours(21), "Diner", 1500);
    public static final Meal MEAL_4 = new Meal(MEAL_ID_4, DATE_2.plusHours(9), "Breakfast", 510);
    public static final Meal MEAL_5 = new Meal(MEAL_ID_5, DATE_2.plusHours(14), "Lunch", 2000);
    public static final Meal MEAL_6 = new Meal(MEAL_ID_6, DATE_2.plusHours(21), "Diner", 1500);

    public static final List<Meal> ALL_MEALS = Arrays.asList(MEAL_3, MEAL_2, MEAL_1);
    public static final List<Meal> NOT_ALL_MEALS = Arrays.asList(MEAL_3, MEAL_1);
    public static final List<Meal> NOT_ALL_MEALS_2 = Arrays.asList(MEAL_2, MEAL_1);
    public static final List<Meal> EMTY = Collections.emptyList();

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getDateTime(), actual.getDateTime())
                            && Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                            && Objects.equals(expected.getCalories(), actual.getCalories())
                    )
    );

}
