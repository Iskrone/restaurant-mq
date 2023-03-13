package me.example.rabbitpractice.data.service;

import lombok.AllArgsConstructor;
import me.example.rabbitpractice.data.model.Dish;
import me.example.rabbitpractice.data.model.Order;
import me.example.rabbitpractice.data.model.OrderItem;
import me.example.rabbitpractice.data.repository.MenuRepository;
import me.example.rabbitpractice.data.repository.OrderRepository;
import me.example.rabbitpractice.data.repository.RestaurantRepository;
import me.example.rabbitpractice.rest.CreateOrderDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Iskander on 21.02.2023
 */
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public List<Order> getOrders() {
        return repository.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {
        return repository.getReferenceById(orderId);
    }

    @Override
    @Transactional
    public List<Dish> getDishesInOrder(Long orderId) {
        List<OrderItem> orderItems = getOrderById(orderId).getItems();
        return orderItems.stream().map(OrderItem::getDish).toList();
    }

    @Override
    @Transactional
    public Order createOrder(Long restaurantId, CreateOrderDTO orderDTO) {
        List<OrderItem> orderItems =
                orderDTO.getItems().stream().map(dto -> OrderItem.builder()
                        .dish(menuRepository.getReferenceById(dto.dishId()))
                        .build()).collect(Collectors.toList());
        Order order2save = Order.builder()
                .items(orderItems)
                .restaurant(restaurantRepository.getReferenceById(restaurantId))
                .build();
        return repository.save(order2save);
    }

    @Override
    public List<Order> getOrdersByRestaurant(Long restaurantId) {
        return repository.findAllByRestaurantId(restaurantId);
    }
}
