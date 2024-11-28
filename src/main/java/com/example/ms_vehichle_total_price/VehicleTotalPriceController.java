package com.example.ms_vehichle_total_price;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/vehicle-total-price")
public class VehicleTotalPriceController {

    /**
     * VehicleTotalPriceService
     */
    private final VehicleTotalPriceService vehicleTotalPriceService;

    /**
     * VehicleSeasonPriceService
     */
    private final VehicleSeasonPriceService vehicleSeasonPriceService;

    /**
     * Constructor
     * @param vehicleTotalPriceService the vehicle total price service
     * @param vehicleSeasonPriceService the vehicle season price service
     */
    public VehicleTotalPriceController(
            VehicleTotalPriceService vehicleTotalPriceService,
            VehicleSeasonPriceService vehicleSeasonPriceService
    ) {
        this.vehicleTotalPriceService = vehicleTotalPriceService;
        this.vehicleSeasonPriceService = vehicleSeasonPriceService;
    }

    /**
     * Calculate the total price for a given number of days for a vehicle type and season
     *
     * @param vehicleType the vehicle type
     * @param season the season
     * @param days the number of days
     */
    @GetMapping("/by-days")
    public Map<String, Object> getTotalPriceByDays(
            @RequestParam("vehicleType") String vehicleType,
            @RequestParam("season") String season,
            @RequestParam("days") Integer days
    ) {
        Double dailyRate = vehicleSeasonPriceService.getDailyRate(vehicleType, season);

        Double totalPrice = vehicleTotalPriceService.getTotalPriceByDays(dailyRate, days);

        Map<String, Object> response = new HashMap<>();

        response.put("totalPrice", totalPrice);
        response.put("vehicleType", vehicleType);
        response.put("season", season);
        response.put("days", days);
        response.put("success", totalPrice != null);

        return response;
    }
}
