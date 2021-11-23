package com.opendev3.devjournal.controller;

import com.opendev3.devjournal.dto.UserDTO;
import com.opendev3.devjournal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "/member/login";
    }


    @PostMapping("/loginSuccess")
    public String loginSuccess() {
        log.info("로그인 진입");
        return "redirect:/todo/list";
    }

}
