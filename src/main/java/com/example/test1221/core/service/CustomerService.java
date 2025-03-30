package com.example.test1221.core.service;

import com.example.test1221.api.dto.PostCustomerDto;
import com.example.test1221.core.exception.EntityNotFoundException;
import com.example.test1221.core.exception.ExistenceException;
import com.example.test1221.core.exception.ValidationException;
import com.example.test1221.core.model.Customer;
import com.example.test1221.core.model.Goal;
import com.example.test1221.core.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer createCustomer(PostCustomerDto customer) {
        var goal = switch (customer.getGoal().toLowerCase()) {
            case "lose_weight" -> Goal.LOSE_WEIGHT;
            case "keep_weight" -> Goal.KEEP_WEIGHT;
            case "gain_weight" -> Goal.GAIN_WEIGHT;
            default -> throw new ValidationException("Invalid goal");
        };

        validateCustomer(customer);

        Customer newCustomer = Customer.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .email(customer.getEmail())
                .gender(customer.getMale())
                .height(customer.getHeight())
                .weight(customer.getWeight())
//                .created_at(LocalDate.now())
                .created_at(LocalDate.of(2025, Month.MARCH, 25))
                .goal(goal)
                .build();

        return customerRepository.save(newCustomer);
    }

    private boolean checkEmail(String email) {
        return customerRepository.findByEmail(email).isPresent();

    }

    public Customer findCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(
                () -> new EntityNotFoundException("Customer not found with id: " + customerId)
        );
    }

    public int getDailyCalories(long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new EntityNotFoundException("Customer not found with id: " + customerId)
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

    private void validateCustomer(PostCustomerDto customer) {
        if (!Pattern.compile("^(.+)@(\\S+) $").matcher(customer.getEmail()).matches()) {
            throw new ValidationException("Email is not valid: " + customer.getEmail());
        }
        if (checkEmail(customer.getEmail())) {
            throw new ExistenceException("Email address is already in use");
        }
        if (customer.getAge() < 12 || customer.getAge() > 100) {
            throw new ValidationException("Age must be between 12 and 100");
        }
        if (customer.getHeight() < 120 || customer.getHeight() > 230) {
            throw new ValidationException("Height must be between 120 and 230");
        }
        if (customer.getWeight() < 30 || customer.getWeight() > 200) {
            throw new ValidationException("Weight must be between 30 and 200");
        }
    }
}
