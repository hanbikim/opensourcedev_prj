package com.opendev3.devjournal.service;

import com.opendev3.devjournal.domain.repository.ProjectRepository;
import com.opendev3.devjournal.dto.MainProjectDto;
import com.opendev3.devjournal.dto.ProjectFormDto;
import com.opendev3.devjournal.dto.ProjectSearchDto;
import com.opendev3.devjournal.domain.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;

    // 상품 등록
    public Long saveProject(ProjectFormDto projectFormDto) {

        // 상품 등록 (1번)
        Project project = projectFormDto.createProject();
        return projectRepository.save(project).getPrj_Id();
    }


    // 상품 조회
    @Transactional(readOnly = true)
    public ProjectFormDto getProjectDetail(Long projectId) {
            // 상품 엔티티를 ItemFormDto 객체로 변환하고 itemImgDtoList 멤버변수를 초기화
            Project project = projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new);
            ProjectFormDto projectFormDto = ProjectFormDto.of(project);
            return projectFormDto;

        }

    // 상품 수정
    public Long updateProject(ProjectFormDto projectFormDto) throws IOException {

        // 상품 수정
        Project project = projectRepository.findById(projectFormDto.getPrj_Id()).orElseThrow(EntityNotFoundException::new);
        project.updateProject(projectFormDto);

        return project.getPrj_Id();
    }

    // 메인 페이지 상품 목록 조회
    @Transactional(readOnly = true)
    public Page<MainProjectDto> getMainProjectPage(ProjectSearchDto projectSearchDto, Pageable pageable){
        return projectRepository.getMainProjectPage(projectSearchDto, pageable);
    }

    @Transactional
    public void deleteProject(Long projectId){
        projectRepository.deleteById(projectId);
    }
}

