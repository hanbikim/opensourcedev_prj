package com.opendev3.devjournal.dto;

import com.opendev3.devjournal.domain.entity.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;

@Getter @Setter
public class MainProjectDto{

    private Long prj_Id;
    private String Title;
    private String Author;
    private String Deadline;
    private String GitHub;
    private String UsingTech;
    private String UsingLang;
    private String Purpose;
    private String feDescription;



    @QueryProjection
    public MainProjectDto(Long prj_Id, String Title, String Author,String feDescription,String Deadline,String GitHub,String UsingTech,String UsingLang,String Purpose){
        this.prj_Id = prj_Id;
        this.Title = Title;
        this.Author= Author;
        this.feDescription = feDescription;
        this.Deadline=Deadline;
        this.GitHub=GitHub;
        this.UsingLang=UsingLang;
        this.UsingTech=UsingTech;
        this.Purpose=Purpose;
    }

}