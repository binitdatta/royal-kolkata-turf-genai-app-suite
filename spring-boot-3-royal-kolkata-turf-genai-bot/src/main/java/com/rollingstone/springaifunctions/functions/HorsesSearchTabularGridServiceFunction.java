package com.rollingstone.springaifunctions.functions;

import com.rollingstone.springaifunctions.model.HorseAnalyticsRequest;
import com.rollingstone.springaifunctions.model.HorseSearchRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

/**
 * Created by Binit Datta.
 */
public class HorsesSearchTabularGridServiceFunction implements Function<HorseSearchRequest, String> {

    Logger logger  = LoggerFactory.getLogger("HorsesByWinsBarChartServiceFunction");
    public static final String HORSES_BY_WINS = "http://localhost:8079/api/analytics/search";

    public HorsesSearchTabularGridServiceFunction() {

    }

    @Override
    public String apply(HorseSearchRequest horseSearchRequest) {
        logger.info("Inside HorsesSearchTabularGridServiceFunction");
        RestClient    restClient = RestClient.builder()
                    .baseUrl(HORSES_BY_WINS)
                    .defaultHeaders(httpHeaders -> {
                        httpHeaders.set("Accept", "application/json");
                        httpHeaders.set("Content-Type", "application/json");
                    }).build();

        return restClient.get().uri(uriBuilder -> {
            if ((horseSearchRequest.birthDate() != null) && !StringUtils.isEmpty(horseSearchRequest.birthDate())){
                uriBuilder.queryParam("birthDate", horseSearchRequest.birthDate());
            }
            if ((horseSearchRequest.color() != null) && !StringUtils.isEmpty(horseSearchRequest.color())){
                uriBuilder.queryParam("color", horseSearchRequest.color());
            }
            if ((horseSearchRequest.gender() != null) && !StringUtils.isEmpty(horseSearchRequest.gender())){
                uriBuilder.queryParam("gender", horseSearchRequest.gender());
            }
            if ((horseSearchRequest.category() != null) && !StringUtils.isEmpty(horseSearchRequest.category())){
                uriBuilder.queryParam("categoryId", horseSearchRequest.category());
            }
            if ((horseSearchRequest.handicapRating() != null) && !StringUtils.isEmpty(horseSearchRequest.handicapRating())){
                uriBuilder.queryParam("handicapRating", horseSearchRequest.handicapRating());
            }
            logger.info("Building URI for jira request: " + horseSearchRequest);
            logger.info("Building URI for jira request: " + uriBuilder.toUriString());

            return uriBuilder.build();
        }).retrieve().body(String.class);
    }
}























