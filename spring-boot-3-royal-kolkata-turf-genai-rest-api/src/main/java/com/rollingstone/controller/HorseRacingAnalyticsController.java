package com.rollingstone.controller;

import com.rollingstone.entity.Horse;
import com.rollingstone.model.*;
import com.rollingstone.services.HorseRacingAnalyticsService;
import com.rollingstone.services.HorseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "http://localhost:4200, http://localhost:4300, http://localhost:4400") // Allow requests from Angular app
public class HorseRacingAnalyticsController {

    Logger logger  = LoggerFactory.getLogger("HorseRacingAnalyticsController");

    @Autowired
    private HorseRacingAnalyticsService service;

    @Autowired
    private HorseService horseService;

    @GetMapping("/total-races-per-category") // bar chart
    public ResponseEntity<List<TotalRacesPerCategory>> getTotalRacesPerCategory() {
        return ResponseEntity.ok(service.getTotalRacesPerCategory());
    }

    @GetMapping("/bets-by-race") // bar chart
    public ResponseEntity<List<TotalBetsByRace>> getTotalBetsByRace() {
        return ResponseEntity.ok(service.getTotalBetsByRace());
    }

    @GetMapping("/horses-by-race") //TODO
    public ResponseEntity<List<HorsesByGender>> getHorsesByRace() {
        return ResponseEntity.ok(service.getHorsesByGender());
    }

    @GetMapping("/cat-wise-handicap") // bar chart
    public ResponseEntity<List<CategoryWiseHandicap>> getCategoryWiseHandicap() {
        return ResponseEntity.ok(service.getCategoryWiseHandicap());
    }

    @GetMapping("/jockey-by-race-participated") // bar chart
    public ResponseEntity<List<JockeysByRaceParticipated>> getJockeysByRaceParticipated() {
        return ResponseEntity.ok(service.getJockeysByRaceParticipated());
    }

    @GetMapping("/ticket-sales-by-race") //TODO
    public ResponseEntity<List<TicketSalesByRace>> getTicketSalesByRace() {
        return ResponseEntity.ok(service.getTicketSalesByRace());
    }

    @GetMapping("/avg-time-per-horse-per-race") // TODO
    public ResponseEntity<List<AvgTimeTakenByHorsePerRace>> getAvgTimeTakenByHorsePerRace() {
        return ResponseEntity.ok(service.getAvgTimeTakenByHorsePerRace());
    }

    @GetMapping("/horse-by-wins") // bar chart
    public ResponseEntity<List<HorsesByWins>> getHorsesByWins() {
        return ResponseEntity.ok(service.getHorsesByWins());
    }

    @GetMapping("/membership-by-type") //pie cart
    public ResponseEntity<List<MembershipByType>> getMembershipByType() {
        return ResponseEntity.ok(service.getMembershipByType());
    }

    @GetMapping("/monthly-revenue-by-party-bookings")
    public ResponseEntity<List<MonthlyRevenyeByPartyBookings>> getMonthlyRevenueByPartyBookings() {
        return ResponseEntity.ok(service.getMonthlyRevenueByPartyBookings());
    }

    @GetMapping("/revenue-by-payouts") //TODO
    public ResponseEntity<List<RevenueByPayouts>> getRevenueByPayouts() {
        return ResponseEntity.ok(service.getRevenueByPayouts());
    }

    @GetMapping("/horse-category-by-weight")
    public ResponseEntity<List<HorseCategoryByWeight>> getHorseCategoryByWeight() {
        return ResponseEntity.ok(service.getHorseCategoryByWeight());
    }

    @GetMapping("/daily-club-revenue-by-tickets-orders") //TODO
    public ResponseEntity<List<DailyClubRevenueByTicketsAndOrder>> getDailyClubRevenueByTicketsAndOrder() {
        return ResponseEntity.ok(service.getDailyClubRevenueByTicketsAndOrder());
    }

    @GetMapping("/betting-outcome-distribution")
    public ResponseEntity<List<BettingOutcome>> getBettingOutcomeDistribution() {
        return ResponseEntity.ok(service.getBettingOutcomeDistribution());
    }

    @GetMapping("/revenue-by-race")
    public ResponseEntity<List<RevenueByRace>> getRevenueByRace() {
        return ResponseEntity.ok(service.getRevenueByRace());
    }

    @GetMapping("/food-and-drinks-popularity")
    public ResponseEntity<List<FoodAndDrinkPopularity>> getFoodAndDrinksPopularity() {
        return ResponseEntity.ok(service.getFoodAndDrinksPopularity());
    }
// /api/analytics/search
    @GetMapping("/search/by-birthdate")
    public ResponseEntity<List<Horse>> findByBirthDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDate) {

        logger.info("Received Birthdate: " + birthDate);
        return ResponseEntity.ok(horseService.findByBirthDate(birthDate));
    }

    // ✅ Search by color
    @GetMapping("/search/by-color")
    public ResponseEntity<List<Horse>> findByColor(@RequestParam String color) {
        return ResponseEntity.ok(horseService.findByColor(color));
    }

    // ✅ Search by gender
    @GetMapping("/search/by-gender")
    public ResponseEntity<List<Horse>> findByGender(@RequestParam Horse.Gender gender) {
        return ResponseEntity.ok(horseService.findByGender(gender));
    }

    // ✅ Search by category ID
    @GetMapping("/search/by-category")
    public ResponseEntity<List<Horse>> findByCategory(@RequestParam Integer categoryId) {
        return ResponseEntity.ok(horseService.findByCategory(categoryId));
    }

    // ✅ Search by handicap rating (greater than or equal to)
    @GetMapping("/search/by-handicap")
    public ResponseEntity<List<Horse>> findByHandicap(@RequestParam BigDecimal handicapRating) {
        return ResponseEntity.ok(horseService.findByHandicapRating(handicapRating));
    }

    // ✅ Search dynamically using multiple filters
    @GetMapping("/search")
    public ResponseEntity<List<Horse>> searchHorses(
            @RequestParam(required = false) String horseName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDate,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) BigDecimal handicapRating) {
        logger.info("HorseName : " + horseName);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();  // Start tracking time

        List<Horse> horses = horseService.searchHorses(horseName, birthDate, color, gender, categoryId, handicapRating);

        stopWatch.stop();  // Stop tracking time
        logger.info("Total execution time: {} ms", stopWatch.getTotalTimeMillis());

        return ResponseEntity.ok(horses);
    }

    @GetMapping("/total-horses")
    public ResponseEntity<Integer> getTotalHorses() {
        return ResponseEntity.ok(horseService.getTotalHorses());
    }

    @GetMapping("/horses-by-category")
    public ResponseEntity<List<Map<String, Object>>> getHorsesByCategory() {
        return ResponseEntity.ok(horseService.getHorsesByCategory());
    }

    @GetMapping("/total-races")
    public ResponseEntity<Integer> getTotalRaces() {
        return ResponseEntity.ok(horseService.getTotalRaces());
    }

    @GetMapping("/races-won-by-horse")
    public ResponseEntity<List<Map<String, Object>>> getRacesWonByHorse() {
        return ResponseEntity.ok(horseService.getRacesWonByHorse());
    }

    @GetMapping("/total-jockeys")
    public ResponseEntity<Integer> getTotalJockeys() {
        return ResponseEntity.ok(horseService.getTotalJockeys());
    }

    @GetMapping("/jockey-race-count")
    public ResponseEntity<List<Map<String, Object>>> getJockeyRaceCount() {
        return ResponseEntity.ok(horseService.getJockeyRaceCount());
    }

    @GetMapping("/fastest-race-time")
    public ResponseEntity<Map<String, Object>> getFastestRaceTime() {
        return ResponseEntity.ok(horseService.getFastestRaceTime());
    }

    @GetMapping("/most-expensive-party-booking")
    public ResponseEntity<Map<String, Object>> getMostExpensivePartyBooking() {
        return ResponseEntity.ok(horseService.getMostExpensivePartyBooking());
    }



    @GetMapping("/food-and-drink-count")
    public ResponseEntity<List<Map<String, Object>>> getFoodAndDrinksCount() {
        return ResponseEntity.ok(horseService.getFoodAndDrinksCount());
    }

    @GetMapping("/total-club-members")
    public ResponseEntity<Integer> getTotalClubMembers() {
        return ResponseEntity.ok(horseService.getTotalClubMembers());
    }
}
