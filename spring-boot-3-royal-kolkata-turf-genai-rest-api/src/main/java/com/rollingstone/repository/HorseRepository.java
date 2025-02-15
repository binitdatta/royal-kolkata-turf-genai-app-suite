package com.rollingstone.repository;

import com.rollingstone.entity.Horse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface HorseRepository extends JpaRepository<Horse, Integer> {

    // Find by birth date
//    @Query("SELECT h FROM Horse h WHERE FUNCTION('DATE', h.birthDate) = :birthDate")
    List<Horse> findByBirthDate(@Param("birthDate") Date birthDate);

//    List<Horse> findByBirthDate(LocalDate birthDate);
    // Find by color
    List<Horse> findByColor(String color);

    // Find by gender
    List<Horse> findByGender(Horse.Gender gender);

    // Find by category ID
    List<Horse> findByCategory_CategoryId(Integer categoryId);

    // Find by handicap rating (greater than or equal to)
    List<Horse> findByHandicapRatingGreaterThanEqual(BigDecimal handicapRating);

    @Query(value = "SELECT * FROM horses h " +
            "WHERE (:horseName IS NULL OR LOWER(h.name) LIKE :horseName) " +
            "AND (:birthDate IS NULL OR h.birth_date = :birthDate) " +
            "AND (:color IS NULL OR h.color = :color) " +
            "AND (:gender IS NULL OR h.gender = :gender) " +
            "AND (:categoryId IS NULL OR h.category_id = :categoryId) " +
            "AND (:handicapRating IS NULL OR h.handicap_rating >= :handicapRating)",
            nativeQuery = true)
    List<Horse> searchHorses(
            @Param("horseName") String horseName,
            @Param("birthDate") Date birthDate,
            @Param("color") String color,
            @Param("gender") String gender,
            @Param("categoryId") Integer categoryId,
            @Param("handicapRating") BigDecimal handicapRating
    );
}
