package com.example.test1221.core.model;

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
    @Column(name = "dish_id")
    private Long dishId;

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
