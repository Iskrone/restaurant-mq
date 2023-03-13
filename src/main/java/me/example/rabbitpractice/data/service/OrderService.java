package me.example.rabbitpractice.data.service;

import me.example.rabbitpractice.data.model.Dish;
import me.example.rabbitpractice.data.model.Order;
import me.example.rabbitpractice.rest.CreateOrderDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Iskander on 21.02.2023
 */
@Transactional
public interface OrderService {
    List<Order> getOrders();

    Order getOrderById(Long orderId);

    @Transactional
    List<Dish> getDishesInOrder(Long orderId);

    Order createOrder(Long restaurantId, CreateOrderDTO orderDTO);

    List<Order> getOrdersByRestaurant(Long restaurantId);
}
