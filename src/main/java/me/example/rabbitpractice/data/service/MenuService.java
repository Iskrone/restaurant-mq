package me.example.rabbitpractice.data.service;

import me.example.rabbitpractice.data.model.Dish;

import java.util.List;

/**
 * @author Iskander on 20.02.2023
 */
public interface MenuService {
    List<Dish> getMenu(Long restaurantId);
}
