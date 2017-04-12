package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

/**
 * Created by apyreev on 11-Apr-17.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService mealService;

    @Autowired
    private DbPopulator dbPopulator;



    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        Meal meal = mealService.get(MEAL_ID_1, USER_ID);
        MATCHER.assertEquals(MEAL_1, meal);
    }

    @Test
    public void testDelete() throws Exception {
        mealService.delete(MEAL_ID_2, USER_ID);
        MATCHER.assertCollectionEquals(NOT_ALL_MEALS, mealService.getAll(USER_ID));
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        LocalDate start = DATE_1.toLocalDate();
        LocalDate end = DATE_1.plusDays(2).toLocalDate();
        LocalDate start2 = DATE_1.plusDays(10).toLocalDate();
        LocalDate end2 = DATE_1.plusDays(12).toLocalDate();
        List<Meal> meals1 = mealService.getBetweenDates(start, end, USER_ID);
        List<Meal> meals2 = mealService.getBetweenDates(start2, end2, USER_ID);
        MATCHER.assertCollectionEquals(ALL_MEALS, meals1);
        MATCHER.assertCollectionEquals(EMTY, meals2);
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        LocalDateTime start = DATE_1.plusHours(8);
        LocalDateTime end = DATE_1.plusHours(15);
        List<Meal> meals = mealService.getBetweenDateTimes(start, end, USER_ID);
        MATCHER.assertCollectionEquals(NOT_ALL_MEALS_2, meals);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(ALL_MEALS, mealService.getAll(USER_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        Meal meal = new Meal(MEAL_ID_3, DATE_1.plusHours(21), "Diner", 1510);
        mealService.update(meal, USER_ID);
        MATCHER.assertEquals(meal, mealService.get(MEAL_ID_3, USER_ID));
    }

    @Test
    public void testSave() throws Exception {
        Meal meal = new Meal(DATE_1.plusHours(21), "Diner", 1510);
        Meal newMeal = mealService.save(meal, USER_ID);
        MATCHER.assertEquals(newMeal, mealService.get(MEAL_ID_6 + 1, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundGet() throws Exception {
        mealService.get(MEAL_ID_1, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        mealService.delete(MEAL_ID_1, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundUpdate() throws Exception {
        Meal meal = new Meal(MEAL_ID_3, DATE_1.plusHours(21), "Diner", 1510);
        mealService.update(meal, ADMIN_ID);
    }
}
