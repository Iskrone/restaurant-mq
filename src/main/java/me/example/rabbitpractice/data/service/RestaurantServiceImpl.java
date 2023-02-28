package me.example.rabbitpractice.data.service;

import lombok.AllArgsConstructor;
import me.example.rabbitpractice.data.model.Restaurant;
import me.example.rabbitpractice.data.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;

    @Override
    public List<Restaurant> getRestaurants() {
        return repository.findAll();
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return repository.getReferenceById(id);
    }

}
