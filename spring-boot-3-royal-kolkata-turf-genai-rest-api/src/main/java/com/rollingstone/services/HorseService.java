package com.rollingstone.services;

import com.rollingstone.entity.Horse;
import com.rollingstone.repository.HorseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HorseService {

    Logger logger  = LoggerFactory.getLogger("HorseService");

    @Autowired
    private HorseRepository repository;

    private final JdbcTemplate jdbcTemplate;

    public HorseService(HorseRepository repository, JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Horse> getAllHorses() {
        return repository.findAll();
    }

    public Optional<Horse> getHorseById(Integer id) {
        return repository.findById(id);
    }

    public Horse createHorse(Horse horse) {
        if (horse.getCategory() != null){
            logger.info("Cat not null");
        }else {
            logger.info("Cat null");
        }
        return repository.save(horse);
    }

    public Horse updateHorse(Integer id, Horse horse) {
        if (repository.existsById(id)) {
            horse.setHorseId(id);
            return repository.save(horse);
        }
        throw new RuntimeException("Horse not found with id: " + id);
    }

    public void deleteHorse(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Horse not found with id: " + id);
        }
    }

    // Search methods
//    public List<Horse> findByBirthDate(Date birthDate) {
//        return repository.findByBirthDate(birthDate);
//    }

    public List<Horse> findByBirthDate(LocalDate birthDate) {
        Date sqlDate = Date.from(birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return repository.findByBirthDate(sqlDate);
    }

    public List<Horse> findByColor(String color) {
        return repository.findByColor(color);
    }

    public List<Horse> findByGender(Horse.Gender gender) {
        return repository.findByGender(gender);
    }

    public List<Horse> findByCategory(Integer categoryId) {
        return repository.findByCategory_CategoryId(categoryId);
    }

    public List<Horse> findByHandicapRating(BigDecimal handicapRating) {
        return repository.findByHandicapRatingGreaterThanEqual(handicapRating);
    }

    // Search horses dynamically
    public List<Horse> searchHorses(String horseName, LocalDate birthDate, String color, String gender, Integer categoryId, BigDecimal handicapRating) {
        Date sqlDate = null;
        if (birthDate != null) {
            sqlDate =  Date.from(birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            logger.info("Birthdate : "+sqlDate);
        }
        String processedHorseName = (horseName == null || horseName.isBlank()) ? null : "%" + horseName.toLowerCase() + "%";
        if (processedHorseName != null){
            logger.info("processedHorseName : "+processedHorseName);
        }
        if (gender != null){
            logger.info("gender : "+gender);
        }
        if (color != null){
            logger.info("color : "+color);
        }

        return repository.searchHorses(processedHorseName, sqlDate, color, gender, categoryId, handicapRating);
    }

    // 1️⃣ Count of Total Horses
    public int getTotalHorses() {
        String sql = "SELECT COUNT(*) FROM HORSES";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // 2️⃣ Count of Horses by Category
    public List<Map<String, Object>> getHorsesByCategory() {
        String sql = "SELECT hc.CATEGORY_NAME, COUNT(h.HORSE_ID) AS horse_count " +
                "FROM HORSE_CATEGORIES hc " +
                "LEFT JOIN HORSES h ON hc.CATEGORY_ID = h.CATEGORY_ID " +
                "GROUP BY hc.CATEGORY_NAME";
        return jdbcTemplate.queryForList(sql);
    }

    // 3️⃣ Count of Races Held
    public int getTotalRaces() {
        String sql = "SELECT COUNT(*) FROM RACES";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // 4️⃣ Count of Races Won by a Horse
    public List<Map<String, Object>> getRacesWonByHorse() {
        String sql = "SELECT h.NAME AS horse_name, COUNT(rw.RACE_WIN_ID) AS races_won " +
                "FROM HORSES h " +
                "JOIN RACES_WON rw ON h.HORSE_ID = rw.HORSE_ID " +
                "GROUP BY h.NAME " +
                "ORDER BY races_won DESC";
        return jdbcTemplate.queryForList(sql);
    }

    // 5️⃣ Count of Jockeys Registered
    public int getTotalJockeys() {
        String sql = "SELECT COUNT(*) FROM JOCKEYS";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // 6️⃣ Count of Races Each Jockey Participated In
    public List<Map<String, Object>> getJockeyRaceCount() {
        String sql = "SELECT j.FULL_NAME, COUNT(rp.PARTICIPATION_ID) AS races_participated " +
                "FROM JOCKEYS j " +
                "JOIN RACE_PARTICIPANTS rp ON j.JOCKEY_ID = rp.JOCKEY_ID " +
                "GROUP BY j.FULL_NAME " +
                "ORDER BY races_participated DESC";
        return jdbcTemplate.queryForList(sql);
    }

    // 7️⃣ Count of Total Club Members
    public int getTotalClubMembers() {
        String sql = "SELECT COUNT(*) FROM CLUB_MEMBERS";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // 8️⃣ Count of Club Members by Membership Type
    public List<Map<String, Object>> getMembersByMembershipType() {
        String sql = "SELECT MEMBERSHIP_TYPE, COUNT(*) AS member_count FROM CLUB_MEMBERS GROUP BY MEMBERSHIP_TYPE";
        return jdbcTemplate.queryForList(sql);
    }

    // 9️⃣ Find the Most Expensive Party Booking
    public Map<String, Object> getMostExpensivePartyBooking() {
        String sql = "SELECT FULL_NAME, VENUE, TOTAL_COST FROM PARTY_BOOKINGS pb " +
                "JOIN CLUB_MEMBERS cm ON pb.MEMBER_ID = cm.MEMBER_ID " +
                "ORDER BY TOTAL_COST DESC LIMIT 1";
        return jdbcTemplate.queryForMap(sql);
    }

    // 🔟 Count of Food and Drinks Available
    public List<Map<String, Object>> getFoodAndDrinksCount() {
        String sql = "SELECT CATEGORY, COUNT(*) AS total_items FROM FOOD_AND_DRINKS GROUP BY CATEGORY";
        return jdbcTemplate.queryForList(sql);
    }

    // 1️⃣1️⃣ Find the Fastest Time Recorded in a Race
    public Map<String, Object> getFastestRaceTime() {
        String sql = "SELECT r.RACE_NAME, h.NAME AS horse_name, rr.TIME_TAKEN " +
                "FROM RACE_RESULTS rr " +
                "JOIN HORSES h ON rr.HORSE_ID = h.HORSE_ID " +
                "JOIN RACES r ON rr.RACE_ID = r.RACE_ID " +
                "ORDER BY rr.TIME_TAKEN ASC LIMIT 1";
        return jdbcTemplate.queryForMap(sql);
    }
}
