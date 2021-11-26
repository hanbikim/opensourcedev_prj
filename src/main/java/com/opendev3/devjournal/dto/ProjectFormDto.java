package com.opendev3.devjournal.dto;

import com.opendev3.devjournal.domain.entity.Project;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class ProjectFormDto {

    private Long prj_Id;

    @NotBlank(message = "프로젝트명은 필수 입력 값입니다.")
    private String Title;

    @NotBlank(message = "필수 입력 값입니다.")
    private String Author;

    @NotBlank(message = "필수 입력 값입니다.")
    private String EffectiveDate;

    @NotBlank(message = "필수 입력 값입니다.")
    private String Deadline;

    @NotBlank(message = "필수 입력 값입니다.")
    private String GitHub;

    @NotBlank(message = "필수 입력 값입니다.")
    private String UsingTech;

    @NotBlank(message = "필수 입력 값입니다.")
    private String UsingLang;

    @NotBlank(message = "필수 입력 값입니다.")
    private String Purpose;

    @NotBlank(message = "필수 입력 값입니다.")
    private String feDescription;



    private static ModelMapper modelMapper = new ModelMapper();

    // DTO -> Entity
    public Project createProject(){

        return modelMapper.map(this, Project.class);
    }

    // Entity -> DTO
    public static ProjectFormDto of(Project project){
        return modelMapper.map(project,ProjectFormDto.class);

    }
}
