package me.example.rabbitpractice.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MainController {

    @GetMapping("/restaurants")
    public void getRestaurantList() {

    }

    @GetMapping("/restaurants/{id}")
    public void getRestaurantInfo(@PathVariable Long id) {

    }

    @GetMapping("/restaurants/{id}/dishes")
    public void getDishesList(@PathVariable Long id) {

    }

    @PostMapping("/restaurants/{id}/dishes/")
    public void createOrder(@PathVariable Long id) {

    }

    @GetMapping("/orders")
    public void getOrdersList() {

    }

    @GetMapping("/orders/{id}")
    public void getOrder(@PathVariable Long id) {

    }

}
