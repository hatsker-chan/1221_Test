package com.example.test1221.core.repository;

import com.example.test1221.core.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Long> {

    Optional<Dish> findByTitle(String title);

    boolean existsByTitle(String title);
}
