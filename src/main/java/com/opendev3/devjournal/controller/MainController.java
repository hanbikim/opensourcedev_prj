package com.opendev3.devjournal.controller;

import com.opendev3.devjournal.dto.MainProjectDto;
import com.opendev3.devjournal.dto.ProjectSearchDto;
import com.opendev3.devjournal.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ProjectService projectService;

    @GetMapping(value = "/dash")
    public String main(ProjectSearchDto projectSearchDto, Optional<Integer> page, Model model) {

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 100);
        Page<MainProjectDto> projects = projectService.getMainProjectPage(projectSearchDto, pageable);

        model.addAttribute("projects", projects);
        model.addAttribute("projectSearchDto", projectSearchDto);
        model.addAttribute("maxPage", 5);
        return "project/Cards";

    }
}
