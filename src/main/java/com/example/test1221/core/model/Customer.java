package com.example.test1221.core.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "age")
    private short age;

    @Column(name = "gender")
    private boolean gender;

    @Column(name = "weight", nullable = false)
    private float weight;

    @Column(name = "height", nullable = false)
    private float height;

    @Enumerated(EnumType.STRING)
    @Column(name = "goal", nullable = false)
    private Goal goal;

    @Column(name = "created_at")
    private LocalDate created_at;

    public boolean getGender() {
        return gender;
    }
}
