package com.opendev3.devjournal.domain.entity;

import com.opendev3.devjournal.dto.ProjectFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="project")
@Getter
@Setter
@ToString
public class Project{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="prj_Id")
    private Long prj_Id;                    //프로젝트 넘버 코드

    @Column(nullable=false, length = 50)
    private String Title;               //프로젝트 명

    @Column(nullable=false, length = 50)
    private String Author;               //프로젝트 제작자명

    @Column(nullable=false, length = 50)
    private String EffectiveDate;               //시작날짜

    @Column(nullable=false, length = 50)
    private String Deadline;               //마감일

    @Column(nullable=false, length = 50)
    private String GitHub;               //깃헙 주소

    @Column(nullable=false, length = 50)
    private String UsingTech;               //사용한 기술

    @Column(nullable=false, length = 50)
    private String UsingLang;               //사용한 언어

    @Lob
    @Column(nullable=false)
    private String feDescription;       //프로젝트 상세설명


    public void updateProject(ProjectFormDto projectFormDto) {
        this.Title = projectFormDto.getTitle();
        this.Author=projectFormDto.getAuthor();
        this.Deadline=projectFormDto.getDeadline();
        this.EffectiveDate=projectFormDto.getEffectiveDate();
        this.GitHub=projectFormDto.getGitHub();
        this.UsingTech=projectFormDto.getUsingTech();
        this.UsingLang=projectFormDto.getUsingLang();
        this.feDescription = projectFormDto.getFeDescription();
    }



}

