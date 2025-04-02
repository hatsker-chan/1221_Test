package com.example.test1221.core.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Long mealId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    private String note;

    private LocalDateTime date;

    @ManyToMany
    @JoinTable(
            name = "meals_dishes",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id")
    )
    private Set<Dish> dishes;
}
