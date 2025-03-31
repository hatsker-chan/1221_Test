package com.example.test1221.serviceTest;

import com.example.test1221.api.dto.PostCustomerDto;
import com.example.test1221.core.exception.ExistenceException;
import com.example.test1221.core.exception.ValidationException;
import com.example.test1221.core.model.Customer;
import com.example.test1221.core.repository.CustomerRepository;
import com.example.test1221.core.service.CustomerService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

@SpringBootTest
@ExtendWith({MockitoExtension.class, SpringExtension.class})
public class CustomerServiceTest {

    @MockitoBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

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
                Arguments.of(
                        createCustomerWithEmail("exist@mail.com"),
                        ExistenceException.class
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
        lenient().when(customerRepository.existsByEmail(anyString())).thenReturn(
                customer.getEmail().equals("exist@mail.com")
        );
        lenient().when(customerRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        if (expectedException != null) {
//            System.out.println(customerRepository.findByEmail("existing@example.com").isPresent());
            assertThrows(expectedException, () -> customerService.createCustomer(customer));
        } else {
            assertDoesNotThrow(() -> customerService.createCustomer(customer));
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
