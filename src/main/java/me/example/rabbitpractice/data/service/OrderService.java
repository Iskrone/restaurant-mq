package me.example.rabbitpractice.data.service;

import me.example.rabbitpractice.data.model.Order;
import me.example.rabbitpractice.rest.OrderDTO;

import java.util.List;

public interface OrderService {
    void createOrder(OrderDTO orderDTO);

    List<Order> findAll();
}
