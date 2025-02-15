package com.rollingstone.springaifunctions.functions;

import com.rollingstone.springaifunctions.model.HorseAnalyticsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

/**
 * Created by Binit Datta.
 */
public class RaceByJockeyParticipationBarChartServiceFunction implements Function<HorseAnalyticsRequest, String> {

    Logger logger  = LoggerFactory.getLogger("RaceByJockeyParticipationBarChartServiceFunction");
    public static final String RACE_BY_JOCKEY_PARTICIPATION = "http://localhost:8079/api/analytics/jockey-by-race-participated";

    public RaceByJockeyParticipationBarChartServiceFunction() {

    }

    @Override
    public String apply(HorseAnalyticsRequest horseAnalyticsRequest) {
        logger.info("Inside RaceByJockeyParticipationBarChartServiceFunction");
        RestClient    restClient = RestClient.builder()
                    .baseUrl(RACE_BY_JOCKEY_PARTICIPATION)
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























