package me.example.rabbitpractice.data.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "ORDERS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private static final String ID_SEQ_NAME = "ORDER_ID_SEQUENCE";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQ_NAME)
    @SequenceGenerator(name = ID_SEQ_NAME, sequenceName = ID_SEQ_NAME, allocationSize = 1)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creation;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @Singular
    private List<OrderItem> items;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

}
