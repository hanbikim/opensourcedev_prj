package com.opendev3.devjournal.domain.repository;

import com.opendev3.devjournal.domain.entity.DevJournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevJournalRepository extends JpaRepository<DevJournalEntity, Long>{
    List<DevJournalEntity> findByPrjTitleContaining(String keyword);
}
