package me.example.rabbitpractice.data.service;

import me.example.rabbitpractice.data.model.Dish;
import me.example.rabbitpractice.data.repository.MenuRepository;

import java.util.List;

/**
 * @author Iskander on 20.02.2023
 */
public class MenuServiceImpl implements MenuService {

    private MenuRepository repository;

    @Override
    public List<Dish> getMenu(Long restaurantId) {
        return repository.findByRestaurantId(restaurantId);
    }
}
