package com.rollingstone.repository;

import com.rollingstone.entity.Jockey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JockeyRepository extends JpaRepository<Jockey, Integer> {
}
