package com.opendev3.devjournal.domain.repository;

import com.opendev3.devjournal.dto.MainProjectDto;
import com.opendev3.devjournal.dto.ProjectSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectRepositoryCustom {

    // @QueryProjection 을 이용하여 바로 Dto 객체 반환
    Page<MainProjectDto> getMainProjectPage(ProjectSearchDto projectSearchDto, Pageable pageable);
}
