package me.example.rabbitpractice.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Iskander on 22.02.2023
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {
    private List<OrderItemDTO> items;
}
