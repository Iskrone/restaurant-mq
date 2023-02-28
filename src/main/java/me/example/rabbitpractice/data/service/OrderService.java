package me.example.rabbitpractice.data.service;

import me.example.rabbitpractice.data.model.Order;
import me.example.rabbitpractice.data.model.OrderItem;
import me.example.rabbitpractice.rest.CreateOrderDTO;

import java.util.List;

/**
 * @author Iskander on 21.02.2023
 */
public interface OrderService {
    List<Order> getOrders();

    Order getOrderById(Long orderId);

    List<OrderItem> getDishesInOrder(Long orderId);

    Order createOrder(Long restaurantId, CreateOrderDTO orderDTO);

    List<Order> getOrdersByRestaurant(Long restaurantId);
}
