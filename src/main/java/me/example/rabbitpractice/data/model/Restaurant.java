package me.example.rabbitpractice.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "RESTAURANTS")
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    private static final String ID_SEQ_NAME = "RESTAURANTS_ID_SEQUENCE";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQ_NAME)
    @SequenceGenerator(name = ID_SEQ_NAME, sequenceName = ID_SEQ_NAME, allocationSize = 1)
    private Long id;

    private String name;

    private String address;
}
