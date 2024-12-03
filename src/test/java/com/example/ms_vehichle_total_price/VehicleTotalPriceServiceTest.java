package com.example.ms_vehichle_total_price;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleTotalPriceServiceTest {

    @Test
    void testGetTotalPriceByDays() {
        // Arrange
        VehicleTotalPriceService service = new VehicleTotalPriceService();
        Double dailyRate = 50.0;
        Integer days = 5;
        Double expectedTotalPrice = 250.0;

        // Act
        Double actualTotalPrice = service.getTotalPriceByDays(dailyRate, days);

        // Assert
        assertEquals(expectedTotalPrice, actualTotalPrice);
    }
}