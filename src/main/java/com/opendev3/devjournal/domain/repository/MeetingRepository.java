package com.opendev3.devjournal.domain.repository;

import com.opendev3.devjournal.domain.entity.MeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRepository extends JpaRepository<MeetingEntity, Long> {
    List<MeetingEntity> findByMprjTitleContaining(String keyword);
}
