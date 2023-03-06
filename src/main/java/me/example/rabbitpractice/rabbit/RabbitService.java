package me.example.rabbitpractice.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import me.example.rabbitpractice.rest.CreateOrderDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Iskander on 02.03.2023
 */
@Service
public class RabbitService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public RabbitService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void createOrderLater(Long restaurantId, CreateOrderDTO orderDTO) throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(new CreateOrderMQDTO(orderDTO, restaurantId));

        rabbitTemplate.convertAndSend(RabbitConfig.topicExchangeName, "foo.bar.baz", jsonString);
        System.out.println("Order posted in Queue...");
    }
}
