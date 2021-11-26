package com.opendev3.devjournal.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainProjectDto{

    private Long prj_Id;
    private String Title;
    private String Author;
    private String feDescription;

    @QueryProjection
    public MainProjectDto(Long prj_Id, String Title, String Author,String feDescription){
        this.prj_Id = prj_Id;
        this.Title = Title;
        this.Author= Author;
        this.feDescription = feDescription;
    }

}