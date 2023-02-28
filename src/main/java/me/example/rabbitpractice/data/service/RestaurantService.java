package me.example.rabbitpractice.data.service;

import me.example.rabbitpractice.data.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {
    List<Restaurant> getRestaurants();

    Restaurant getRestaurantById(Long id);
}
