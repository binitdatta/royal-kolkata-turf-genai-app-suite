package com.rollingstone.repository;

import com.rollingstone.entity.RaceParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceParticipantRepository extends JpaRepository<RaceParticipant, Integer> {
}
