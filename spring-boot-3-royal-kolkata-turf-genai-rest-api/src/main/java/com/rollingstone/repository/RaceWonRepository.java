package com.rollingstone.repository;

import com.rollingstone.entity.RaceWon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceWonRepository extends JpaRepository<RaceWon, Integer> {
}
