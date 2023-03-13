package me.example.rabbitpractice.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "DISHES")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dish {

    private static final String ID_SEQ_NAME = "DISH_ID_SEQUENCE";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQ_NAME)
    @SequenceGenerator(name = ID_SEQ_NAME, sequenceName = ID_SEQ_NAME, allocationSize = 1)
    private Long id;

    private String name;

    private String description;

    private double price;

    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable=false)
    private Restaurant restaurant;
}
