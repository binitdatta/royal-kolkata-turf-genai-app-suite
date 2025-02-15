package com.rollingstone.repository;

import com.rollingstone.entity.BettingPayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BettingPayoutRepository extends JpaRepository<BettingPayout, Integer> {
}
