package com.opendev3.devjournal.domain.repository;

import com.opendev3.devjournal.domain.entity.DevJournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevJournalRepository extends JpaRepository<DevJournalEntity, Long>{
}
