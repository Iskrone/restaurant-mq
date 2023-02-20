package me.example.rabbitpractice.rest;

import me.example.rabbitpractice.data.model.Restaurant;
import me.example.rabbitpractice.data.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    private final RestaurantService restaurantService;

    public MainController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<Restaurant>> getRestaurantList() {
        return ResponseEntity.ok(restaurantService.getRestaurants());
    }

    @GetMapping("/restaurants/{id}")
    public ResponseEntity<Restaurant> getRestaurantInfo(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }
}
