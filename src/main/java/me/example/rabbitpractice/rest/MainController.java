package me.example.rabbitpractice.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import me.example.rabbitpractice.data.model.Restaurant;
import me.example.rabbitpractice.data.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rp")
public class MainController {

    private final RestaurantService restaurantService;

    public MainController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurants")
    @Operation(summary = "Получить список ресторанов",
            description = "Возвращает список ресторанов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Успешно"),
            @ApiResponse(responseCode = "500",
                    description = "Любая другая ошибка сервиса или БД",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )})})
    public ResponseEntity<List<Restaurant>> getRestaurantList() {
        return ResponseEntity.ok(restaurantService.getRestaurants());
    }

    @Operation(summary = "Получить информацию о ресторане",
            description = "Получить информацию о ресторане по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Успешно"),
            @ApiResponse(responseCode = "500",
                    description = "Любая другая ошибка сервиса или БД",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )})})
    @GetMapping("/restaurants/{id}")
    public ResponseEntity<Restaurant> getRestaurantInfo(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }
}
