package me.example.rabbitpractice.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import me.example.rabbitpractice.data.model.Dish;
import me.example.rabbitpractice.data.model.Order;
import me.example.rabbitpractice.data.model.OrderItem;
import me.example.rabbitpractice.data.model.Restaurant;
import me.example.rabbitpractice.data.service.MenuService;
import me.example.rabbitpractice.data.service.OrderService;
import me.example.rabbitpractice.data.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rp")
public class MainController {

    private final RestaurantService restaurantService;
    private final MenuService menuService;
    private final OrderService orderService;

    public MainController(RestaurantService restaurantService,
                          MenuService menuService, OrderService orderService) {
        this.restaurantService = restaurantService;
        this.menuService = menuService;
        this.orderService = orderService;
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
    public void createOrder(@PathVariable Long id, OrderDTO orderDTO) {

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
    public ResponseEntity<List<OrderItem>> getDishesInOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getDishesInOrder(id));
    }
}