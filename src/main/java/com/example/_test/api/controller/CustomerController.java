package com.example._test.api.controller;

import com.example._test.api.dto.PostCustomerDto;
import com.example._test.core.model.Customer;
import com.example._test.core.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public Customer postCustomer(@RequestBody PostCustomerDto customerDto) {
        return customerService.createCustomer(customerDto);
    }

    @GetMapping("/{customerId}/daily_сalories")
    public float getDailyCalories(@PathVariable long customerId) {
        return customerService.getDailyCalories(customerId);
    }
}
