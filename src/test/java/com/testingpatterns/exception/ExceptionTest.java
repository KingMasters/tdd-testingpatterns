package com.testingpatterns.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
    Exception tipi net
    Mesaj da test edilebilir
    Okunabilir (Given–When–Then’e çok uygun)
 */
public class ExceptionTest {


    @Test
    void shouldThrowExceptionWhenDividerIsZero() {
        //Given
        Calculator calculator = new Calculator();

        //When
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class,
                        () -> calculator.divide(10, 0));

        //Then
        assertEquals("Divider cannot be zero", ex.getMessage());
    }

}
