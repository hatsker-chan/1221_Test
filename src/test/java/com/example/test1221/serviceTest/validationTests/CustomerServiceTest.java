package com.example.test1221.serviceTest.validationTests;

import com.example.test1221.api.dto.PostCustomerDto;
import com.example.test1221.core.exception.ValidationException;
import com.example.test1221.core.util.Validator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerServiceTest {

    public static Stream<Arguments> customerValidationTestCases() {
        return Stream.of(
                // Valid customer (should not throw exception)
                Arguments.of(
                        createValidCustomer(),
                        null
                ),
                // Invalid email format
                Arguments.of(
                        createCustomerWithEmail("invalid-email"),
                        ValidationException.class
                ),
                // Age too low
                Arguments.of(
                        createCustomerWithAge((short) 11),
                        ValidationException.class
                ),
                // Age too high
                Arguments.of(
                        createCustomerWithAge((short) 101),
                        ValidationException.class
                ),
                // Height too low
                Arguments.of(
                        createCustomerWithHeight(119),
                        ValidationException.class
                ),
                // Height too high
                Arguments.of(
                        createCustomerWithHeight(231),
                        ValidationException.class
                ),
                // Weight too low
                Arguments.of(
                        createCustomerWithWeight(29),
                        ValidationException.class
                ),
                // Weight too high
                Arguments.of(
                        createCustomerWithWeight(201),
                        ValidationException.class
                )
        );
    }

    @ParameterizedTest
    @MethodSource("customerValidationTestCases")
    void testValidateCustomer(PostCustomerDto customer, Class<? extends Exception> expectedException) {

        if (expectedException != null) {
            assertThrows(expectedException, () -> Validator.validateCustomer(customer));
        } else {
            assertDoesNotThrow(() -> Validator.validateCustomer(customer));
        }
    }

    private static PostCustomerDto createValidCustomer() {
        PostCustomerDto dto = new PostCustomerDto();
        dto.setEmail("alex@gmail.com");
        dto.setAge((short) 30);
        dto.setHeight(170);
        dto.setWeight(70);
        dto.setGoal("keep_weight");
        return dto;
    }

    private static PostCustomerDto createCustomerWithEmail(String email) {
        PostCustomerDto dto = createValidCustomer();
        dto.setEmail(email);
        return dto;
    }

    private static PostCustomerDto createCustomerWithAge(short age) {
        PostCustomerDto dto = createValidCustomer();
        dto.setAge(age);
        return dto;
    }

    private static PostCustomerDto createCustomerWithHeight(int height) {
        PostCustomerDto dto = createValidCustomer();
        dto.setHeight(height);
        return dto;
    }

    private static PostCustomerDto createCustomerWithWeight(int weight) {
        PostCustomerDto dto = createValidCustomer();
        dto.setWeight(weight);
        return dto;
    }
}
