package com.example.test1221.core.repository;

import com.example.test1221.core.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Long> {

    Optional<List<Meal>> findAllByCustomerCustomerIdAndDateBetween(Long customerId, LocalDateTime start, LocalDateTime end);
}
