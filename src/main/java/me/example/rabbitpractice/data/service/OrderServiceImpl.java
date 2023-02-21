package me.example.rabbitpractice.data.service;

import me.example.rabbitpractice.data.model.Order;
import me.example.rabbitpractice.data.model.OrderItem;
import me.example.rabbitpractice.data.repository.OrderRepository;
import me.example.rabbitpractice.rest.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Iskander on 21.02.2023
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Order> getOrders() {
        return repository.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {
        return repository.getReferenceById(orderId);
    }

    @Override
    public List<OrderItem> getDishesInOrder(Long orderId) {
        return repository.getReferenceById(orderId).getItems();
    }

    @Override
    public void createOrder(OrderDTO orderDTO) {

    }

    @Override
    public List<Order> getOrdersByRestaurant(Long restaurantId) {
        return repository.findAllByRestaurantId(restaurantId);
    }
}
