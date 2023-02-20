package me.example.rabbitpractice.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import me.example.rabbitpractice.data.model.Dish;
import me.example.rabbitpractice.data.model.Restaurant;
import me.example.rabbitpractice.data.service.MenuService;
import me.example.rabbitpractice.data.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    private RestaurantService restaurantService;
    private MenuService menuService;

    @GetMapping("/restaurants")
//    @Operation(summary = "List of Restaurants")
//    @ApiResponses(value = @ApiResponse(responseCode = "200",
//            description = "Успешно",
//            content = {@Content(
//                    mediaType = "application/json",
//                    schema = @Schema(implementation = String.class))}))
    public ResponseEntity<List<Restaurant>> getRestaurantList() {
        return ResponseEntity.ok(restaurantService.getRestaurants());
    }

    @GetMapping("/restaurants/{id}")
    public ResponseEntity<Restaurant> getRestaurantInfo(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }

    @GetMapping("/restaurants/{id}/menu")
    public ResponseEntity<List<Dish>> getMenu(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.getMenu(id));
    }

    @PostMapping("/restaurants/{id}/order/")
    public void createOrder(@PathVariable Long id) {

    }

    @GetMapping("/orders")
    public void getOrdersList() {

    }

    @GetMapping("/orders/{id}")
    public void getOrder(@PathVariable Long id) {

    }

}
