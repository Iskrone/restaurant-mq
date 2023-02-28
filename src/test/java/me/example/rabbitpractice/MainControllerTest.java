package me.example.rabbitpractice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.example.rabbitpractice.data.model.Dish;
import me.example.rabbitpractice.data.model.Order;
import me.example.rabbitpractice.data.model.OrderItem;
import me.example.rabbitpractice.data.model.Restaurant;
import me.example.rabbitpractice.data.service.MenuService;
import me.example.rabbitpractice.data.service.OrderService;
import me.example.rabbitpractice.data.service.RestaurantService;
import me.example.rabbitpractice.rest.CreateOrderDTO;
import me.example.rabbitpractice.rest.MainController;
import me.example.rabbitpractice.rest.OrderItemDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

/**
 * @author Iskander on 22.02.2023
 */
@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    RestaurantService restaurantService;

    @MockBean
    MenuService menuService;

    @MockBean
    OrderService orderService;

    @Test
    public void getAllRestaurantsTest() throws Exception {
        Restaurant restaurant1 = new Restaurant(1L, "Ромашка", "Улица Пушкина");
        Restaurant restaurant2 = new Restaurant(2L, "Клевер", "Улица Лермонтова");
        Restaurant restaurant3 = new Restaurant(3L, "Лилия", "Улица Горького");
        List<Restaurant> records = new ArrayList<>(Arrays.asList(restaurant1, restaurant2, restaurant3));

        Mockito.when(restaurantService.getRestaurants()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/rp/restaurants")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
//                .andExpect(jsonPath("$[2].name", is("Jane Doe")));
    }

    @Test
    public void createOrder() throws Exception {
        Restaurant restaurant = new Restaurant(1L, "Ромашка", "Улица Пушкина");
        Dish dish1 = Dish.builder()
                .id(1L).name("Булка").description("").price(12.3).restaurant(restaurant).build();
        Dish dish2 = Dish.builder()
                .id(2L)
                .name("Коржик").description("Коржик с молоком")
                .price(23.7).restaurant(restaurant).build();

        List<OrderItemDTO> items = new ArrayList<>();
        items.add(new OrderItemDTO(dish1.getId()));
        items.add(new OrderItemDTO(dish2.getId()));
        CreateOrderDTO record = CreateOrderDTO.builder()
                .items(items)
                .build();

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(1L, dish1));
        orderItems.add(new OrderItem(2L, dish2));
        Order order = Order.builder().restaurant(restaurant).id(99L).items(orderItems).build();

        Mockito.when(orderService.createOrder(restaurant.getId(), record)).thenReturn(order);

        MockHttpServletRequestBuilder mockRequest =
                MockMvcRequestBuilders.post("/rp/restaurants/1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(record));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(99)));
    }
}
