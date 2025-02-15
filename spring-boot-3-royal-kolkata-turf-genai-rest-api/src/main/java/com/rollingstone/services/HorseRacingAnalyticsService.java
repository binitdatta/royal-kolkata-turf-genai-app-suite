package com.rollingstone.services;

import com.rollingstone.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorseRacingAnalyticsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TotalRacesPerCategory> getTotalRacesPerCategory() {
        String query = """
            SELECT 
                hc.CATEGORY_NAME AS category,
                COUNT(r.RACE_ID) AS total_races
            FROM 
                HORSE_CATEGORIES hc
            LEFT JOIN 
                RACES r ON hc.CATEGORY_ID = r.CATEGORY_ID
            GROUP BY 
                hc.CATEGORY_NAME;
        """;

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            TotalRacesPerCategory result = new TotalRacesPerCategory();
            result.setCategory(rs.getString("category"));
            result.setTotalRaces(rs.getLong("total_races"));
            return result;
        });
    }

    public List<HorsesByGender> getHorsesByGender() {
        String query = """
            SELECT 
                GENDER,
                COUNT(HORSE_ID) AS total_horses
            FROM 
                HORSES
            GROUP BY 
                GENDER;
        """;

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            HorsesByGender result = new HorsesByGender();
            result.setGender(rs.getString("GENDER"));
            result.setTotalHorses(rs.getLong("total_horses"));
            return result;
        });
    }

    public List<TotalBetsByRace> getTotalBetsByRace() {
        String query = """
                   SELECT 
                     r.RACE_NAME AS race,
                      COUNT(b.BET_ID) AS total_bets
                      FROM
                      RACES r
                     LEFT JOIN 
                    BETTING b ON r.RACE_ID = b.RACE_ID
                   GROUP BY
                     r.RACE_NAME;       
                """;

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            TotalBetsByRace result = new TotalBetsByRace();
            result.setRace(rs.getString("race"));
            result.setTotalBets(rs.getLong("total_bets"));
            return result;
        });
    }

    public List<CategoryWiseHandicap> getCategoryWiseHandicap() {
        String query = """
                SELECT 
                  hc.CATEGORY_NAME AS category,
                 AVG(h.HANDICAP_RATING) AS avg_handicap_rating
                 FROM
                 HORSE_CATEGORIES hc
                 LEFT JOIN
                 HORSES h ON hc.CATEGORY_ID = h.CATEGORY_ID
                 GROUP BY 
                 hc.CATEGORY_NAME;                 
                """;

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            CategoryWiseHandicap result = new CategoryWiseHandicap();
            result.setCategory(rs.getString("category"));
            result.setAverageHandiCapRating(rs.getLong("avg_handicap_rating"));
            return result;
        });
    }

    public List<JockeysByRaceParticipated> getJockeysByRaceParticipated() {
        String query = """
                SELECT
                 j.FULL_NAME AS jockey,
                COUNT(rp.PARTICIPATION_ID) AS total_races
                FROM
                JOCKEYS j
                 LEFT JOIN\s
                 RACE_PARTICIPANTS rp ON j.JOCKEY_ID = rp.JOCKEY_ID
                GROUP BY
                j.FULL_NAME
                 ORDER BY
                total_races DESC
                 LIMIT 10;            
                """;

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            JockeysByRaceParticipated result = new JockeysByRaceParticipated();
            result.setJockey(rs.getString("jockey"));
            result.setRaceParticipated(rs.getLong("total_races"));
            return result;
        });
    }

    public List<TicketSalesByRace> getTicketSalesByRace() {
        String query = """
                SELECT 
                r.RACE_NAME AS race,
                SUM(t.PRICE) AS total_revenue
                FROM 
                RACES r
                LEFT JOIN 
                 TICKETS t ON r.RACE_ID = t.RACE_ID
                 GROUP BY 
                 r.RACE_NAME;    
                """;

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            TicketSalesByRace result = new TicketSalesByRace();
            result.setRace(rs.getString("race"));
            result.setRevenue(rs.getBigDecimal("total_revenue"));
            return result;
        });
    }

    public List<AvgTimeTakenByHorsePerRace> getAvgTimeTakenByHorsePerRace() {
        String query = """
                SELECT
                r.RACE_NAME AS race,
                rr.POSITION AS position,
                AVG(rr.TIME_TAKEN) AS avg_time
                FROM
                RACES r
                LEFT JOIN
                RACE_RESULTS rr ON r.RACE_ID = rr.RACE_ID
                WHERE
                 rr.POSITION <= 3
                 GROUP BY
                   r.RACE_NAME, rr.POSITION
                 ORDER BY
                 r.RACE_NAME, rr.POSITION;
                """;

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            AvgTimeTakenByHorsePerRace result = new AvgTimeTakenByHorsePerRace();
            result.setRace(rs.getString("race"));
            result.setPosition(rs.getInt("position"));
            result.setTimeTaken(rs.getFloat("avg_time"));
            return result;
        });
    }

    public List<HorsesByWins> getHorsesByWins() {
        String query = """
                SELECT
                 h.NAME AS horse_name,
                  COUNT(rw.RACE_WIN_ID) AS total_wins
                  FROM
                 HORSES h
                 LEFT JOIN
                 RACES_WON rw ON h.HORSE_ID = rw.HORSE_ID
                GROUP BY
                 h.NAME
                ORDER BY
                total_wins DESC
                  LIMIT 10;
                """;

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            HorsesByWins result = new HorsesByWins();
            result.setHorseName(rs.getString("horse_name"));
            result.setWins(rs.getLong("total_wins"));
            return result;
        });
    }

    public List<MembershipByType> getMembershipByType() {
        String query = """
                SELECT
                 MEMBERSHIP_TYPE,
                 COUNT(MEMBER_ID) AS total_members
                 FROM
                CLUB_MEMBERS
                GROUP BY
                 MEMBERSHIP_TYPE;
                """;

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            MembershipByType result = new MembershipByType();
            result.setMembershipType(rs.getString("MEMBERSHIP_TYPE"));
            result.setTotalMembers(rs.getInt("total_members"));
            return result;
        });
    }

    public List<MonthlyRevenyeByPartyBookings> getMonthlyRevenueByPartyBookings() {
        String query = """
                SELECT
                                                    MONTH(EVENT_DATE) month,
                                                    MONTHNAME(DATE(EVENT_DATE)) AS monthname,
                                                    YEAR(EVENT_DATE) AS year,
                                                    SUM(TOTAL_COST) AS total_revenue
                                                FROM
                                                    PARTY_BOOKINGS
                                                GROUP BY
                                                    YEAR(EVENT_DATE), MONTH(EVENT_DATE), MONTHNAME(DATE(EVENT_DATE))
                                                ORDER BY
                                                    year, month;
                """;

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            MonthlyRevenyeByPartyBookings result = new MonthlyRevenyeByPartyBookings();
            result.setMonth(rs.getString("monthname"));
            result.setYear(rs.getString("year"));
            result.setTotalRevenue(rs.getBigDecimal("total_revenue"));
            return result;
        });
    }



    public List<BettingOutcome> getBettingOutcomeDistribution() {
        String query = """
            SELECT 
                BET_OUTCOME,
                COUNT(BET_ID) AS total_bets
            FROM 
                BETTING
            GROUP BY 
                BET_OUTCOME;
        """;

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            BettingOutcome result = new BettingOutcome();
            result.setOutcome(rs.getString("BET_OUTCOME"));
            result.setTotalBets(rs.getLong("total_bets"));
            return result;
        });
    }

    public List<RevenueByPayouts> getRevenueByPayouts() {
        String query = """
                        SELECT\s
                                                       r.RACE_NAME AS race,
                                                       SUM(bp.AMOUNT) AS total_payouts
                                                   FROM\s
                                                       RACES r
                                                   LEFT JOIN\s
                                                       BETTING b ON r.RACE_ID = b.RACE_ID
                                                   LEFT JOIN\s
                                                       BETTING_PAYOUTS bp ON b.BET_ID = bp.BET_ID
                                                   GROUP BY\s
                                                       r.RACE_NAME;
                """;

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            RevenueByPayouts result = new RevenueByPayouts();
            result.setRace(rs.getString("race"));
            result.setTotalPayouts(rs.getBigDecimal("total_payouts"));
            return result;
        });
    }

    public List<HorseCategoryByWeight> getHorseCategoryByWeight() {
        String query = """
                SELECT\s
                    hc.CATEGORY_NAME AS category,
                    AVG(h.WEIGHT) AS avg_weight
                FROM\s
                    HORSE_CATEGORIES hc
                LEFT JOIN\s
                    HORSES h ON hc.CATEGORY_ID = h.CATEGORY_ID
                GROUP BY\s
                    hc.CATEGORY_NAME;
                """;

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            HorseCategoryByWeight result = new HorseCategoryByWeight();
            result.setCategory(rs.getString("category"));
            result.setAvgWeight(rs.getLong("avg_weight"));
            return result;
        });
    }

    public List<DailyClubRevenueByTicketsAndOrder> getDailyClubRevenueByTicketsAndOrder() {
        String query = """
                SELECT\s
                    o.ORDER_DATE AS date,
                    SUM(o.TOTAL_AMOUNT) AS total_orders,
                    SUM(t.PRICE) AS total_tickets
                FROM\s
                    ORDERS o
                LEFT JOIN\s
                    TICKETS t ON DATE(o.ORDER_DATE) = DATE(t.RACE_DATE)
                GROUP BY\s
                    o.ORDER_DATE
                ORDER BY\s
                    o.ORDER_DATE;
                """;

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            DailyClubRevenueByTicketsAndOrder result = new DailyClubRevenueByTicketsAndOrder();
            result.setDate(rs.getString("date"));
            result.setTotalOrders(rs.getLong("total_orders"));
            result.setTotalTickets(rs.getLong("total_tickets"));
            return result;
        });
    }

    public List<RevenueByRace> getRevenueByRace() {
        String query = """
            SELECT 
                r.RACE_NAME AS race,
                SUM(t.PRICE) AS total_revenue
            FROM 
                RACES r
            LEFT JOIN 
                TICKETS t ON r.RACE_ID = t.RACE_ID
            GROUP BY 
                r.RACE_NAME;
        """;

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            RevenueByRace result = new RevenueByRace();
            result.setRaceName(rs.getString("race"));
            result.setTotalRevenue(rs.getDouble("total_revenue"));
            return result;
        });
    }

    public List<FoodAndDrinkPopularity> getFoodAndDrinksPopularity() {
        String query = """
            SELECT 
                fd.ITEM_NAME AS item,
                SUM(od.QUANTITY) AS total_quantity_sold
            FROM 
                FOOD_AND_DRINKS fd
            LEFT JOIN 
                ORDER_DETAILS od ON fd.ITEM_ID = od.ITEM_ID
            GROUP BY 
                fd.ITEM_NAME
            ORDER BY 
                total_quantity_sold DESC;
        """;

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            FoodAndDrinkPopularity result = new FoodAndDrinkPopularity();
            result.setItemName(rs.getString("item"));
            result.setTotalQuantitySold(rs.getLong("total_quantity_sold"));
            return result;
        });
    }
}
