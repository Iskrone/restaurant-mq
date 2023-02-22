package me.example.rabbitpractice.data.service;

import lombok.AllArgsConstructor;
import me.example.rabbitpractice.data.model.Dish;
import me.example.rabbitpractice.data.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Iskander on 21.02.2023
 */
@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository repository;

    public List<Dish> getMenu(Long restaurantId) {
        return repository.findAllByRestaurantId(restaurantId);
    }
}
