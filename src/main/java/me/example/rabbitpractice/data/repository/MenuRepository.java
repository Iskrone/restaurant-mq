package me.example.rabbitpractice.data.repository;

import me.example.rabbitpractice.data.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Iskander on 21.02.2023
 */
public interface MenuRepository extends JpaRepository<Dish, Long> {
    List<Dish> findAllByRestaurantId(Long restaurantId);
}
