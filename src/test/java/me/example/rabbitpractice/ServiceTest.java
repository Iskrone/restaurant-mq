package me.example.rabbitpractice;

import me.example.rabbitpractice.data.model.Dish;
import me.example.rabbitpractice.data.model.Order;
import me.example.rabbitpractice.data.model.Restaurant;
import me.example.rabbitpractice.data.service.MenuService;
import me.example.rabbitpractice.data.service.OrderService;
import me.example.rabbitpractice.data.service.RestaurantService;
import me.example.rabbitpractice.rest.CreateOrderDTO;
import me.example.rabbitpractice.rest.OrderItemDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Iskander on 13.03.2023
 */
@SpringBootTest
public class ServiceTest {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    MenuService menuService;

    @Autowired
    OrderService orderService;

    @Test
    public void getRestaurantsTest() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        Assertions.assertNotEquals(0, restaurants.size());
    }

    @Test
    public void createOrderTest() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        List<Dish> dishes = menuService.getMenu(restaurants.get(0).getId());
        Assertions.assertNotEquals(0, dishes.size());

        List<OrderItemDTO> items = new ArrayList<>();
        items.add(new OrderItemDTO(dishes.get(0).getId()));
        items.add(new OrderItemDTO(dishes.get(0).getId()));
        items.add(new OrderItemDTO(dishes.get(1).getId()));
        CreateOrderDTO orderDTO = CreateOrderDTO.builder()
                .items(items)
                .build();
        Order newOrder = orderService.createOrder(restaurants.get(0).getId(), orderDTO);
        List<Order> ordersRest0 = orderService.getOrdersByRestaurant(restaurants.get(0).getId());
        List<Order> findOrders =
                ordersRest0.stream().filter(order -> order.getId().equals(newOrder.getId())).toList();
        Assertions.assertEquals(1, findOrders.size());

        Order fromDB = findOrders.get(0);
        Assertions.assertEquals(restaurants.get(0).getId(), fromDB.getRestaurant().getId());

        List<Dish> dishesInOrder = orderService.getDishesInOrder(fromDB.getId());

        Assertions.assertEquals(items.size(), dishesInOrder.size());

        HashMap<Long, Integer> dishesMap1 = new HashMap<>();
        for (Dish dish : dishesInOrder) {
            dishesMap1.put(dish.getId(), dishesMap1.getOrDefault(dish.getId(), 0) + 1);
        }
        for (OrderItemDTO orderItem : items) {
            if (dishesMap1.get(orderItem.dishId()) == 1) {
                dishesMap1.remove(orderItem.dishId());
            } else {
                dishesMap1.put(orderItem.dishId(), dishesMap1.get(orderItem.dishId()) - 1);
            }
        }

        Assertions.assertTrue(dishesMap1.isEmpty());
    }
}
