package com.example.test1221.api.controller;

import com.example.test1221.api.dto.DailyCaloriesResponse;
import com.example.test1221.api.dto.PostCustomerDto;
import com.example.test1221.api.exception.ApiExceptionHandling;
import com.example.test1221.core.model.Customer;
import com.example.test1221.core.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ApiExceptionHandling
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
        int dailyCalories = customerService.getDailyCalories(customerId);
        Customer customer = customerService.findCustomerById(customerId);
        var response = new DailyCaloriesResponse(
                customer.getAge(),
                customer.getGender(),
                customer.getWeight(),
                customer.getHeight(),
                customer.getGoal().name(),
                dailyCalories
        );
        return ResponseEntity.ok(response);
    }
}
