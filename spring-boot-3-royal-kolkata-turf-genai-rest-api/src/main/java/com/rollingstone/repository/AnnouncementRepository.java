package com.rollingstone.repository;

import com.rollingstone.entity.Announcement;
import com.rollingstone.entity.Role;
import com.rollingstone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {}




