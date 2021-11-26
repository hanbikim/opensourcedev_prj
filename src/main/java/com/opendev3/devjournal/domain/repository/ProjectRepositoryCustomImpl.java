package com.opendev3.devjournal.domain.repository;
import com.opendev3.devjournal.dto.MainProjectDto;
import com.opendev3.devjournal.dto.ProjectSearchDto;
import com.opendev3.devjournal.dto.QMainProjectDto;
import com.opendev3.devjournal.domain.entity.QProject;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class ProjectRepositoryCustomImpl implements ProjectRepositoryCustom {

    private JPAQueryFactory queryFactory;

    // 생성자 DI를 통해서 JPAQueryFactory(EntityManager) 주입
    public ProjectRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }


    private BooleanExpression projectNameLike(String searchQuery) {
        return StringUtils.isEmpty(searchQuery) ? null : QProject.project.Title.like("%" + searchQuery + "%");
    }

    @Override
    public Page<MainProjectDto> getMainProjectPage(ProjectSearchDto projectSearchDto, Pageable pageable) {

        QProject project = QProject.project;

        QueryResults<MainProjectDto> result = queryFactory
                .select(
                        new QMainProjectDto(
                                project.prj_Id/*,
                                project.Title,
                                project.Author,
                                project.EffectiveDate,
                                project.Deadline,
                                project.GitHub,
                                project.UsingTech,
                                project.UsingLang,
                                project.Purpose,
                                project.feDescription*/)
                )
                .from(project)
                .where(projectNameLike(projectSearchDto.getSearchQuery()))
                .orderBy(project.prj_Id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MainProjectDto> content = result.getResults();
        long total = result.getTotal();
        return new PageImpl<>(content, pageable, total);
    }
}

