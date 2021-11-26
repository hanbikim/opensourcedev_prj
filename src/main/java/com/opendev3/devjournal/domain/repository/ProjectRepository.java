package com.opendev3.devjournal.domain.repository;

import com.opendev3.devjournal.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long>, ProjectRepositoryCustom {
}


