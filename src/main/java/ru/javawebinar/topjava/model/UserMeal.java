package ru.javawebinar.topjava.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
@AllArgsConstructor
public class UserMeal {
    @Getter
    private final LocalDateTime dateTime;
    @Getter
    private final String description;
    @Getter
    private final int calories;
}
