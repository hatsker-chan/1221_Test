package com.example.test1221.api.dto;

import com.example.test1221.core.model.Dish;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PostMealDto {
    private long customerId;

    private String note;

    private List<Dish> dishes;
}
