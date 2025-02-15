package com.rollingstone.springaifunctions.functions;

import com.rollingstone.springaifunctions.model.HorseAnalyticsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

/**
 * Created by Binit Datta.
 */
public class TotalBetsByRaceBarChartServiceFunction implements Function<HorseAnalyticsRequest, String> {

    Logger logger  = LoggerFactory.getLogger("ASInfoCountServiceFunction");
    public static final String BETS_BY_RACE = "http://localhost:8079/api/analytics/bets-by-race";

    public TotalBetsByRaceBarChartServiceFunction() {

    }

    @Override
    public String apply(HorseAnalyticsRequest horseAnalyticsRequest) {
        logger.info("Inside TotalBetsByRaceBarChartServiceFunction");
        RestClient    restClient = RestClient.builder()
                    .baseUrl(BETS_BY_RACE)
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























