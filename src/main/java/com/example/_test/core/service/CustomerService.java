package com.example._test.core.service;

import com.example._test.api.dto.PostCustomerDto;
import com.example._test.core.model.Customer;
import com.example._test.core.model.Goal;
import com.example._test.core.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer createCustomer(PostCustomerDto customer) {
        if (checkEmail(customer.getEmail())) {
            throw new IllegalArgumentException("Email address is already in use");
        }

        var goal = switch (customer.getGoal().toLowerCase()) {
            case "lose_weight" -> Goal.LOSE_WEIGHT;
            case "keep_weight" -> Goal.KEEP_WEIGHT;
            case "gain_weight" -> Goal.GAIN_WEIGHT;
            default -> throw new IllegalArgumentException("Invalid goal");
        };

        Customer newCustomer = Customer.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .email(customer.getEmail())
                .gender(customer.getMale())
                .height(customer.getHeight())
                .weight(customer.getWeight())
                .goal(goal)
                .build();

        return customerRepository.save(newCustomer);
    }

    private boolean checkEmail(String email) {
        return customerRepository.findByEmail(email).isPresent();

    }

    public int getDailyCalories(long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new IllegalArgumentException("Customer not found with id: " + customerId)
        );
        Goal goal = customer.getGoal();

        float bmr;
        if (customer.getGender()) {
            //for male
            bmr = (float) (88.362 + (13.397 * customer.getWeight()) + (4.799 * customer.getHeight()) - (5.677 * customer.getAge()));
        } else {
            //for female
            bmr = (float) (447.593 + (9.247 * customer.getWeight()) + (3.098 * customer.getHeight()) - (4.330 * customer.getAge()));

        }
        bmr *= 1.4f;
        switch (goal) {
            case LOSE_WEIGHT -> bmr *= 0.85f;
            case GAIN_WEIGHT -> bmr *= 1.15f;
        }
        return (int) bmr;
    }

}
