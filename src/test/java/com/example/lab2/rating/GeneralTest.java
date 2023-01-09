package com.example.lab2.rating;

import org.checkerframework.checker.units.qual.Acceleration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GeneralTest {
    @Test
    public void testMath() {
        assertEquals(3, 1 + 2);
    }
}
