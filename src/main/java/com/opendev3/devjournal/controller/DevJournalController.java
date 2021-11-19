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
    @GetMapping("/")
    public String list(Model model) {
        List<DevJournalDto> devjournalList = devjournalService.getDevJournallist();

        model.addAttribute("devjournalList", devjournalList);
        return "devjournal/list.html";
    }

    @GetMapping("/post")
    public String write() {
        return "devjournal/write.html";
    }

    @PostMapping("/post")
    public String write(DevJournalDto devjournalDto) {
        devjournalService.savePost(devjournalDto);
        return "redirect:/";
    }


    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        DevJournalDto devjournalDto = devjournalService.getPost(no);

        model.addAttribute("devjournalDto", devjournalDto);
        return "devjournal/detail.html";
    }

    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        DevJournalDto devjournalDTO = devjournalService.getPost(no);

        model.addAttribute("devjournalDto", devjournalDTO);
        return "devjournal/update.html";
    }


    @PutMapping("/post/edit/{no}")
    public String update(DevJournalDto devjournalDTO) {
        devjournalService.savePost(devjournalDTO);

        return "redirect:/";
    }

    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        devjournalService.deletePost(no);

        return "redirect:/";
    }


}
