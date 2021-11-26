package com.opendev3.devjournal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProjectSearchDto {

    private String searchDateType;
    private String searchBy;
    private String searchQuery = "";
}
