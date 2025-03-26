package com.example._test.core.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dishId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int caloriesPerDish;

    @Column(nullable = false)
    private int protein;

    @Column(nullable = false)
    private int carbohydrates;

    @Column(nullable = false)
    private int fat;
}
