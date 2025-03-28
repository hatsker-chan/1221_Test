package com.example.test1221.api.controller;

import com.example.test1221.api.dto.DailyCaloriesResponse;
import com.example.test1221.api.dto.PostCustomerDto;
import com.example.test1221.core.model.Customer;
import com.example.test1221.core.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public Customer postCustomer(@RequestBody PostCustomerDto customerDto) {
        return customerService.createCustomer(customerDto);
    }

    @GetMapping("/{customerId}/daily_calories")
    public ResponseEntity<DailyCaloriesResponse> getDailyCalories(@PathVariable long customerId) {
        var response = new DailyCaloriesResponse(customerService.getDailyCalories(customerId));
        return ResponseEntity.ok(response);
    }
}
