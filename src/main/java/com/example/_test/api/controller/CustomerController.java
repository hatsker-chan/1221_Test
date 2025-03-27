package com.example._test.api.controller;

import com.example._test.api.dto.DailyCaloriesResponse;
import com.example._test.api.dto.PostCustomerDto;
import com.example._test.core.model.Customer;
import com.example._test.core.service.CustomerService;
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
        var response = new DailyCaloriesResponse(
                customerService.getDailyCalories(customerId),
                0,0,"123"
        );
        return ResponseEntity.ok(response);
    }
}
