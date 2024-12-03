package com.example.ms_vehichle_total_price;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VehicleTotalPriceController.class)
public class VehicleTotalPriceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleTotalPriceService vehicleTotalPriceService;

    @MockBean
    private VehicleSeasonPriceService vehicleSeasonPriceService;


    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetTotalPriceByDays() throws Exception {
        // Arrange
        String vehicleType = "Compact";
        String season = "summer";
        int days = 5;
        double dailyRate = 50.0;
        double totalPrice = 250.0;

        given(vehicleSeasonPriceService.getDailyRate(anyString(), anyString())).willReturn(dailyRate);
        given(vehicleTotalPriceService.getTotalPriceByDays(dailyRate, days)).willReturn(totalPrice);

        // Act & Assert
        mockMvc.perform(get("/vehicle-total-price/by-days")
                        .param("vehicleType", vehicleType)
                        .param("season", season)
                        .param("days", String.valueOf(days))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice").value(totalPrice))
                .andExpect(jsonPath("$.vehicleType").value(vehicleType))
                .andExpect(jsonPath("$.season").value(season))
                .andExpect(jsonPath("$.days").value(days))
                .andExpect(jsonPath("$.success").value(true));
    }
}
