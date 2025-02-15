package com.rollingstone.repository;

import com.rollingstone.entity.PartyBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyBookingRepository extends JpaRepository<PartyBooking, Integer> {
}
