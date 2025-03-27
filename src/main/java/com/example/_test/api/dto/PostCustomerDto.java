package com.example._test.api.dto;

import com.example._test.core.model.Goal;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostCustomerDto {
    private String name;

    private String email;

    private short age;

    private float weight;

    private float height;

    private String goal;
}
