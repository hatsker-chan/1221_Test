package com.example.test1221.core.repository;

import com.example.test1221.core.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
