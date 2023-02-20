package me.example.rabbitpractice.data.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "dish_id", referencedColumnName = "id", nullable = false)
    private Dish name;

    private double price;
}
