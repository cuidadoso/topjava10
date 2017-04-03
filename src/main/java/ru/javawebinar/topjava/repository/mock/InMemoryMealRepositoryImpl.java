package ru.javawebinar.topjava.repository.mock;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);
    //private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private Table<Integer, Integer, Meal> repository = HashBasedTable.create();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach((meal) -> save(meal, 1));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        LOG.info("save " + meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), userId, meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        LOG.info("delete " + id);
        repository.remove(id, userId);
        return Objects.isNull(repository.get(id, userId));
    }

    @Override
    public Meal get(int id, int userId) {
        LOG.info("get " + id);
        return repository.get(id, userId);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        LOG.info("getAll");
        return repository.column(userId).values().stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}

