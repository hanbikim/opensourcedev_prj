package com.opendev3.devjournal.controller;

import com.opendev3.devjournal.dto.DevJournalDto;
import com.opendev3.devjournal.service.DevJournalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class DevJournalController {
    private DevJournalService devjournalService;

    /* 게시글 목록 */
    @GetMapping("/tidy")
    public String list(Model model) {
        List<DevJournalDto> devjournalList = devjournalService.getDevJournallist();

        model.addAttribute("devjournalList", devjournalList);
        return "/tidy/list";
    }

    @GetMapping("/tidy/post")
    public String write() {
        return "/tidy/write";
    }

    @PostMapping("/tidy/post")
    public String write(DevJournalDto devjournalDto) {
        devjournalService.savePost(devjournalDto);
        return "redirect:/tidy";
    }


    @GetMapping("/tidy/post/{no1}")
    public String detail(@PathVariable("no1") Long no1, Model model) {
        DevJournalDto devjournalDto = devjournalService.getPost(no1);

        model.addAttribute("devjournalDto", devjournalDto);
         return "/tidy/detail";
    }

    @GetMapping("/tidy/post/edit/{no1}")
    public String edit(@PathVariable("no1") Long no1, Model model) {
        DevJournalDto devjournalDTO = devjournalService.getPost(no1);

        model.addAttribute("devjournalDto", devjournalDTO);
        return "/tidy/update";
    }


    @PutMapping("/tidy/post/edit/{no1}")
    public String update(DevJournalDto devjournalDTO) {
        devjournalService.savePost(devjournalDTO);

        return "redirect:/tidy";
    }

    @DeleteMapping("/tidy/post/{no1}")
    public String delete(@PathVariable("no1") Long no1) {
        devjournalService.deletePost(no1);

        return "redirect:/tidy";
    }

}
