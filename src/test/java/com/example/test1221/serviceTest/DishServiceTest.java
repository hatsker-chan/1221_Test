package com.example.test1221.serviceTest;

import com.example.test1221.api.dto.PostDishDto;
import com.example.test1221.core.exception.ExistenceException;
import com.example.test1221.core.exception.ValidationException;
import com.example.test1221.core.model.Dish;
import com.example.test1221.core.repository.DishRepository;
import com.example.test1221.core.service.DishService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DishServiceTest {

    @MockitoBean
    private DishRepository dishRepository;

    @Autowired
    private DishService dishService;

    public static Stream<Arguments> dishValidationTestCases() {
        return Stream.of(
                // Valid dish (should not throw exception)
                Arguments.of(
                        createValidDish(),
                        null
                ),
                // Negative calories
                Arguments.of(
                        createDishWithCalories(-10),
                        ValidationException.class
                ),
                // Negative protein
                Arguments.of(
                        createDishWithProtein(-5),
                        ValidationException.class
                ),
                // Negative carbs
                Arguments.of(
                        createDishWithCarbs(-3),
                        ValidationException.class
                ),
                // Negative fat
                Arguments.of(
                        createDishWithFat(-2),
                        ValidationException.class
                ),
                // Empty title
                Arguments.of(
                        createDishWithTitle(""),
                        ValidationException.class
                ),
                // Null title
                Arguments.of(
                        createDishWithTitle(null),
                        ValidationException.class
                ),
                // Calories too low
                Arguments.of(
                        createDishWithCalories(20),
                        ValidationException.class
                ),
                // Calories too high
                Arguments.of(
                        createDishWithCalories(1600),
                        ValidationException.class
                ),
                // Invalid nutrition proportions (30% difference)
                Arguments.of(
                        createDishWithNutrition(100, 10, 10, 10), // 10*4 + 10*4 + 10*9 = 170 vs 100 cals
                        ValidationException.class
                ),
                // Existing dish title
                Arguments.of(
                        createDishWithTitle("Existing Dish"),
                        ExistenceException.class
                )
        );
    }

    @ParameterizedTest
    @MethodSource("dishValidationTestCases")
    void testCreateDish(PostDishDto dishDto, Class<? extends Exception> expectedException) {
        // Mock dish existence check
        lenient().when(dishRepository.existsByTitle(anyString()))
                .thenAnswer(inv -> "Existing Dish".equals(inv.getArgument(0)));

        // Mock save to return the input dish
        lenient().when(dishRepository.save(any(Dish.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        if (expectedException != null) {
            assertThrows(expectedException, () -> dishService.createDish(dishDto));
        } else {
            assertDoesNotThrow(() -> dishService.createDish(dishDto));
        }
    }

    private static PostDishDto createValidDish() {
        return PostDishDto.builder()
                .title("Test Dish")
                .calories(400)
                .protein(30)
                .carbohydrates(35)
                .fat(15)
                .build();
    }

    private static PostDishDto createDishWithCalories(int calories) {
        PostDishDto dto = createValidDish();
        dto.setCalories(calories);
        return dto;
    }

    private static PostDishDto createDishWithProtein(int protein) {
        PostDishDto dto = createValidDish();
        dto.setProtein(protein);
        return dto;
    }

    private static PostDishDto createDishWithCarbs(int carbs) {
        PostDishDto dto = createValidDish();
        dto.setCarbohydrates(carbs);
        return dto;
    }

    private static PostDishDto createDishWithFat(int fat) {
        PostDishDto dto = createValidDish();
        dto.setFat(fat);
        return dto;
    }

    private static PostDishDto createDishWithTitle(String title) {
        PostDishDto dto = createValidDish();
        dto.setTitle(title);
        return dto;
    }

    private static PostDishDto createDishWithNutrition(int calories, int protein, int carbs, int fat) {
        return PostDishDto.builder()
                .title("Nutrition Test")
                .calories(calories)
                .protein(protein)
                .carbohydrates(carbs)
                .fat(fat)
                .build();
    }
}
