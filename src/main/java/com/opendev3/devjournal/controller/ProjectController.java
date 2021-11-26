package com.opendev3.devjournal.controller;

import com.opendev3.devjournal.dto.ProjectFormDto;
import com.opendev3.devjournal.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    // 상품 등록 페이지
    //ProjectFormDto 객체를 model 객체에 담아 뷰로 전달.
    @GetMapping(value = "/project/new")
    public String projectForm(ProjectFormDto projectFormDto, Model model) {

        model.addAttribute("projectFormDto", projectFormDto);
        return "project/user-profile-lite";
    }

//     상품 등록
    @PostMapping(value = "/project/new")
    public String projectNew(@Valid ProjectFormDto projectFormDto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "project/user-profile-lite";

        }


        try {
            projectService.saveProject(projectFormDto/*, projectImgFileList*/);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "project/user-profile-lite";
        }
        return "redirect:/dash";
    }


    // 수정 페이지
    @GetMapping(value = "/project/new/{prj_Id}")
    public String projectDetail(@PathVariable(name = "prj_Id") Long prj_Id, Model model) {

        try {
            ProjectFormDto projectFormDto = projectService.getProjectDetail(prj_Id);
            model.addAttribute("projectFormDto", projectFormDto);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 프로젝트입니다.");
            model.addAttribute("projectFormDto", new ProjectFormDto());
        }
        return "project/user-profile-lite";
    }

    // 수정
    @PostMapping(value = "/project/new/{prj_Id}")
    public String projectUpdate(@Valid ProjectFormDto projectFormDto, BindingResult bindingResult, Model model/*,
                             @RequestParam(name = "projectImgFile") List<MultipartFile> projectImgFileList*/) {

        if (bindingResult.hasErrors()) {
            return "project/user-profile-lite";
        }


        try {
            projectService.updateProject(projectFormDto/*, projectImgFileList*/);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
            return "project/user-profile-lite";
        }
        return "redirect:/dash";
    }

    // 상세 페이지
    @GetMapping(value = "/project/{prj_Id}")
    public String projectDetail(Model model, @PathVariable("prj_Id") Long projectId) {
        ProjectFormDto projectFormDto = projectService.getProjectDetail(projectId);
        model.addAttribute("project", projectFormDto);
        return "project/projectDetail";
    }

    //삭제
    @DeleteMapping("/project/{prj_Id}")
    public String delete(@PathVariable("prj_Id") Long projectId){
        projectService.deleteProject(projectId);
        return "redirect:/dash";
    }

}
