package ru.javawebinar.topjava.model;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
@AllArgsConstructor
@ToString
public class UserMealWithExceed {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean exceed;
}
