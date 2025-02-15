package com.rollingstone.repository;

import com.rollingstone.entity.FoodAndDrink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodAndDrinkRepository extends JpaRepository<FoodAndDrink, Integer> {
}
