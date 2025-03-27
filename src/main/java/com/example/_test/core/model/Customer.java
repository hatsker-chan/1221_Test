package com.example._test.core.model;

import jakarta.persistence.*;
import lombok.*;

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

    public boolean getGender() {
        return gender;
    }
}
