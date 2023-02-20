package me.example.rabbitpractice.data.service;

import me.example.rabbitpractice.data.model.Restaurant;
import me.example.rabbitpractice.data.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantServiceImpl(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return repository.findAll();
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return repository.getReferenceById(id);
    }

}
