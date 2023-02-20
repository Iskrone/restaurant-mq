package me.example.rabbitpractice.data.service;

import me.example.rabbitpractice.data.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> getRestaurants();

    Restaurant getRestaurantById(Long id);
}