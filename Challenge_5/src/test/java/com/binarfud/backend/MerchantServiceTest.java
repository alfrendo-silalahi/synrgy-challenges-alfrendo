package com.binarfud.backend;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BinarfudBackendApplication.class)
public class MerchantServiceTest {

    @Test
    void testExample() {
        System.out.println("Test Example");
        var expected = 10;
        assertEquals(expected, 10, "value must be 10");
    }

    @Test
    void testExample2() {
        System.out.println("Test Example 2");
        var expected = 20;
        assertEquals(expected, 20, "value must be 20");
    }

}
