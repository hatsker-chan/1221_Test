package com.example.test1221.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostCustomerDto {
    private String name;

    private String email;

    private short age;

    private boolean isMale;

    private float weight;

    private float height;

    private String goal;

    public boolean getMale() {
        return isMale;
    }
}
