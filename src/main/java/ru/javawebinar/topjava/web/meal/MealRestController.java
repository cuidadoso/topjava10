package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    protected final Logger LOG = LoggerFactory.getLogger(MealRestController.class);
    private int userId = AuthorizedUser.id();

    @Autowired
    private MealService service;

    public Collection<Meal> getAll() {
        LOG.info("getAll");
        return service.getAll(userId);
    }

    public Meal get(int id) {
        LOG.info("get " + id);
        return service.get(id, userId);
    }

    public Meal create(Meal meal) {
        LOG.info("create " + meal);
        checkNew(meal);
        return service.save(meal, userId);
    }

    public void update(Meal meal, int id) {
        LOG.info("update " + meal);
        checkIdConsistent(meal, id);
        service.update(meal, userId);
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id, userId);
    }
}