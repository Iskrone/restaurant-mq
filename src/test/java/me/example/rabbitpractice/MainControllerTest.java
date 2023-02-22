package me.example.rabbitpractice;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.example.rabbitpractice.data.model.Restaurant;
import me.example.rabbitpractice.data.repository.RestaurantRepository;
import me.example.rabbitpractice.data.service.MenuService;
import me.example.rabbitpractice.data.service.OrderService;
import me.example.rabbitpractice.data.service.RestaurantService;
import me.example.rabbitpractice.rest.MainController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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


//    @Autowired
//    private ObjectMapper mapper;

    @MockBean
    RestaurantService restaurantService;

    @MockBean
    MenuService menuService;

    @MockBean
    OrderService orderService;

    Restaurant RECORD_1 = new Restaurant(1L, "Ромашка", "Улица Пушкина");
    Restaurant RECORD_2 = new Restaurant(2L, "Клевер", "Улица Лермонтова");
    Restaurant RECORD_3 = new Restaurant(3L, "Лилия", "Улица Горького");

    @Test
    public void getAllRestaurantsTest() throws Exception {
        List<Restaurant> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));

        Mockito.when(restaurantService.getRestaurants()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/rp/restaurants")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
//                .andExpect(jsonPath("$[2].name", is("Jane Doe")));
    }
}
