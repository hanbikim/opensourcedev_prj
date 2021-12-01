package com.opendev3.devjournal.domain.repository;

import com.opendev3.devjournal.domain.entity.DevJournalEntity;
import com.opendev3.devjournal.domain.entity.MeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRepository extends JpaRepository<MeetingEntity, Long> {
    static List<MeetingEntity> findByTitleContaining(String keyword) {
        return null;
    }
}
