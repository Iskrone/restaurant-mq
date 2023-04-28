package me.example.rabbitpractice.rabbit;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.example.rabbitpractice.rest.CreateOrderDTO;

/**
 * Order DTO for creating with Rabbit
 * @author Iskander on 02.03.2023
 */
@AllArgsConstructor
@Data
public class CreateOrderMQDTO {
    private CreateOrderDTO orderDTO;
    private Long restaurantId;
}
