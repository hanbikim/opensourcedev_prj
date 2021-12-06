package com.opendev3.devjournal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarController {
    @GetMapping("/calendar")
    public String calendar() {
        return "Calendar/calendar";
    }
}
