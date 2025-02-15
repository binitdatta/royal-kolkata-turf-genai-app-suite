package com.rollingstone.repository;

import com.rollingstone.entity.HorseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorseCategoryRepository extends JpaRepository<HorseCategory, Integer> {
}
