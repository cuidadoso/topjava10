package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    protected final Logger LOG = LoggerFactory.getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public Collection<MealWithExceed> getAll(int userId) {
        LOG.info("getAll");
        return MealsUtil.getWithExceeded(service.getAll(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public Meal get(int id, int userId) {
        LOG.info("get " + id);
        return service.get(id, userId);
    }

    public Meal create(Meal meal, int userId) {
        LOG.info("create " + meal);
        checkNew(meal);
        return service.save(meal, userId);
    }

    public void update(Meal meal, int id, int userId) {
        LOG.info("update " + meal);
        checkIdConsistent(meal, id);
        service.update(meal, userId);
    }

    public void delete(int id, int userId) {
        LOG.info("delete " + id);
        service.delete(id, userId);
    }
}