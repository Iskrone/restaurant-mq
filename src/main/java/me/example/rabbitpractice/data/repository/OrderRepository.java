package me.example.rabbitpractice.data.repository;

import me.example.rabbitpractice.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Iskander on 21.02.2023
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByRestaurantId(Long restaurantId);
}
