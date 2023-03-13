package me.example.rabbitpractice.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import me.example.rabbitpractice.data.model.Dish;
import me.example.rabbitpractice.data.model.Order;
import me.example.rabbitpractice.data.model.Restaurant;
import me.example.rabbitpractice.data.service.MenuService;
import me.example.rabbitpractice.data.service.OrderService;
import me.example.rabbitpractice.data.service.RestaurantService;
import me.example.rabbitpractice.rabbit.RabbitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rp")
@Tag(name = "Main controller", description = "All the operations")
@AllArgsConstructor
public class MainController {

    private final RestaurantService restaurantService;
    private final MenuService menuService;
    private final OrderService orderService;
    private final RabbitService rabbitService;

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

    @Operation(summary = "Get Restaurant Menu",
            description = "Get Restaurant Menu by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Успешно")})
    @GetMapping("/restaurants/{id}/menu")
    public ResponseEntity<List<Dish>> getMenu(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.getMenu(id));
    }

    @Operation(summary = "Get orders list by Restaurant",
            description = "Get orders list by Restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Успешно")})
    @GetMapping("/restaurants/{id}/orders")
    public ResponseEntity<List<Order>> getOrdersForRestaurant(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrdersByRestaurant(id));
    }

    @Operation(summary = "Create new order",
            description = "Make new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Успешно")})
    @PostMapping("/restaurants/{id}/orders")
    public ResponseEntity<Order> createOrder(@PathVariable Long id, @RequestBody CreateOrderDTO orderDTO) {
        return ResponseEntity.ok(orderService.createOrder(id, orderDTO));
    }

    @Operation(summary = "Create new order with MQ",
            description = "Make new order with MQ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Успешно")})
    @PostMapping("/restaurants/{id}/orders/later")
    public ResponseEntity createOrderLater(@PathVariable Long id,
                                           @RequestBody CreateOrderDTO orderDTO) throws JsonProcessingException {
        rabbitService.createOrderLater(id, orderDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get All orders",
            description = "Get All orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Успешно")})
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }

    @Operation(summary = "Get order info",
            description = "Get order info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Успешно")})
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @Operation(summary = "Get dishes in order",
            description = "Get dishes in order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Успешно")})
    @GetMapping("/orders/{id}/dishes")
    public ResponseEntity<List<Dish>> getDishesInOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getDishesInOrder(id));
    }
}