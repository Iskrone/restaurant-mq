package me.example.rabbitpractice.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import me.example.rabbitpractice.data.model.Order;
import me.example.rabbitpractice.data.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Iskander on 02.03.2023
 */
@Component
public class OrderReceiver {

    private final OrderService orderService;

    @Autowired
    ObjectMapper objectMapper;

    @Getter
    private String createdOrderStr;

    public OrderReceiver(OrderService orderService) {
        this.orderService = orderService;
    }

    public void createOrderLater(String message) {
        try {
            CreateOrderMQDTO orderReceived = objectMapper.readValue(message, CreateOrderMQDTO.class);
            Order order = orderService.createOrder(orderReceived.getRestaurantId(), orderReceived.getOrderDTO());
            System.out.println("Order processed & persisted in DB. Order Id: " + order.getId());
            createdOrderStr = message;
        } catch (IOException e) {
            System.out.println("Problem in processing the order");
            e.printStackTrace();
        }
    }
}
