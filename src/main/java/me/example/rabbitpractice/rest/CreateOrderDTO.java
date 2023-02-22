package me.example.rabbitpractice.rest;

import java.util.List;

/**
 * @author Iskander on 22.02.2023
 */
public record CreateOrderDTO(List<OrderItemDTO> items) {
}
