package com.example._test.core.repository;

import com.example._test.core.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Long> {

    Optional<Dish> findByTitle(String title);
}
