package com.iexceed.marketplacesrv.seller.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iexceed.marketplacesrv.seller.response.ChartDataResponse.EarningsChart;

import jakarta.annotation.PostConstruct;

@Service
public class ChartService {
	
	private EarningsChart earningsChartData;

    @PostConstruct
    public void loadStageData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Read the JSON file from resources
        InputStream inputStream = getClass().getResourceAsStream("/chart-data.json");

        // Convert JSON file to EarningsChartResponse
        earningsChartData = objectMapper.readValue(inputStream, EarningsChart.class);
    }

    // Method to return the whole earnings chart data
    public EarningsChart getEarningsChartData() {
        return earningsChartData;
    }

}
