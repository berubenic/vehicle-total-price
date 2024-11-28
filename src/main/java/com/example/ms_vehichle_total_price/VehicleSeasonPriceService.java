package com.example.ms_vehichle_total_price;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class VehicleSeasonPriceService {
    @Value("${vehicle.season.price.url}")
    private String url;

    public Double getDailyRate(String vehicleType, String season) {
        RestTemplate restTemplate = new RestTemplate();

        String requestUrl = UriComponentsBuilder.fromUriString(url)
                .path("/daily-rate")
                .queryParam("vehicleType", vehicleType)
                .queryParam("season", season)
                .toUriString();

        VehicleSeasonPriceResponse response = restTemplate.getForObject(requestUrl, VehicleSeasonPriceResponse.class);

        if (response != null && response.isSuccess()) {
            return response.getDailyRate();
        } else {
            throw new RuntimeException("Failed to get daily rate");
        }
    }
}
