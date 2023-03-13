package me.example.rabbitpractice.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ORDER_ITEMS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    private static final String ID_SEQ_NAME = "ORDER_ITEM_ID_SEQUENCE";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQ_NAME)
    @SequenceGenerator(name = ID_SEQ_NAME, sequenceName = ID_SEQ_NAME, allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "dish_id", referencedColumnName = "id", nullable = false)
    private Dish dish;
}
