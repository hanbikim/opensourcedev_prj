package com.opendev3.devjournal.controller;

import com.opendev3.devjournal.dto.MeetingDto;
import com.opendev3.devjournal.service.MeetingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class MeetingController {
    private MeetingService meetingService;

    /* 게시글 목록 */
    @GetMapping("/meetup")
    public String list(Model model) {
        List<MeetingDto> meetingList = meetingService.getMeetinglist();

        model.addAttribute("meetingList", meetingList);
        return "/meeting/list";
    }

    @GetMapping("/meetup/post")
    public String write() {
        return "/meeting/write";
    }

    @PostMapping("/meetup/post")
    public String write(MeetingDto meetingDto) {
        meetingService.savePost(meetingDto);
        return "redirect:/meetup";
    }


    @GetMapping("/meetup/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        MeetingDto meetingDto = meetingService.getPost(no);

        model.addAttribute("meetingDto", meetingDto);
        return "/meeting/detail";
    }

    @GetMapping("/meetup/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        MeetingDto meetingDTO = meetingService.getPost(no);

        model.addAttribute("meetingDto", meetingDTO);
        return "/meeting/update";
    }


    @PutMapping("/meetup/post/edit/{no}")
    public String update(MeetingDto meetingDTO) {
        meetingService.savePost(meetingDTO);

        return "redirect:/meetup";
    }

    @DeleteMapping("/meetup/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        meetingService.deletePost(no);

        return "redirect:/meetup";
    }

}
