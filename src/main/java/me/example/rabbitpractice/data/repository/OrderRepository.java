package me.example.rabbitpractice.data.repository;

import me.example.rabbitpractice.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
