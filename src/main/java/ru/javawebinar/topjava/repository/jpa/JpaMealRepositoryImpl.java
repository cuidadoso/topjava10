package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            User ref = em.getReference(User.class, userId);
            meal.setUser(ref);
            em.persist(meal);
        } else {
            if (em.createNamedQuery(Meal.UPDATE)
                    .setParameter("id", meal.getId())
                    .setParameter("user_id", userId)
                    .setParameter("description", meal.getDescription())
                    .setParameter("calories", meal.getCalories())
                    .setParameter("date_time", meal.getDateTime())
                    .executeUpdate() == 0) {
                return null;
            }
        }
        return meal;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE).setParameter("id", id).setParameter("user_id", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        try {
            return em.createNamedQuery(Meal.GET, Meal.class).setParameter("id", id).setParameter("user_id", userId)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class).setParameter("user_id", userId).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(Meal.GET_BETWEEN, Meal.class)
                .setParameter("user_id", userId)
                .setParameter("start_time", startDate)
                .setParameter("end_time", endDate)
                .getResultList();
    }
}