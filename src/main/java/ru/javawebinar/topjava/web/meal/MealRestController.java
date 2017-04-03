package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Collection;

@Controller
public class MealRestController {

    private int userId = AuthorizedUser.id();

    @Autowired
    private MealService service;

    public Collection<Meal> getAll() {
        return service.getAll(userId);
    }

    public Meal get(int id) {
        return service.get(id, userId);
    }

    public Meal create(Meal meal) {
        return service.save(meal, userId);
    }


    public void delete(int id) {
        service.delete(id, userId);
    }
}