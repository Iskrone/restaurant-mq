package me.example.rabbitpractice.data.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "DISHES")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private double price;

    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable=false)
    private Restaurant restaurant;
}
