package com.example.ms_vehichle_total_price;

import org.springframework.stereotype.Service;

@Service
public class VehicleTotalPriceService {

    public Double getTotalPriceByDays(Double dailyRate, Integer days) {
        return dailyRate * days;
    }
}
