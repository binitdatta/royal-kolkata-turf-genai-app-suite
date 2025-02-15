package com.rollingstone.springaifunctions.functions;

import com.rollingstone.springaifunctions.model.HorseAnalyticsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

/**
 * Created by Binit Datta.
 */
public class MonthlyRevenueByPartyBookingsChartServiceFunction implements Function<HorseAnalyticsRequest, String> {

    Logger logger  = LoggerFactory.getLogger("MonthlyRevenueByPartyBookingsChartServiceFunction");
    public static final String MONTHLY_REVENUE_BY_PARTY_BOOKINGS = "http://localhost:8079/api/analytics/monthly-revenue-by-party-bookings";

    public MonthlyRevenueByPartyBookingsChartServiceFunction() {

    }

    @Override
    public String apply(HorseAnalyticsRequest horseAnalyticsRequest) {
        logger.info("Inside MonthlyRevenueByPartyBookingsChartServiceFunction");
        RestClient    restClient = RestClient.builder()
                    .baseUrl(MONTHLY_REVENUE_BY_PARTY_BOOKINGS)
                    .defaultHeaders(httpHeaders -> {
                        httpHeaders.set("Accept", "application/json");
                        httpHeaders.set("Content-Type", "application/json");
                    }).build();

        return restClient.get().uri(uriBuilder -> {
            System.out.println("Building URI for jira request: " + horseAnalyticsRequest);
            System.out.println("Building URI for jira request: " + uriBuilder.toUriString());

            return uriBuilder.build();
        }).retrieve().body(String.class);
    }
}























