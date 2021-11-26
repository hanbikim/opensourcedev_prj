package com.opendev3.devjournal.controller;

import com.opendev3.devjournal.domain.entity.ToDoList;
import com.opendev3.devjournal.dto.ReplyDto;
import com.opendev3.devjournal.service.ReplyService;
import com.opendev3.devjournal.domain.entity.ToDoList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/reply")
@RequiredArgsConstructor
@Slf4j
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/checkIdx")
    public ResponseEntity<?> checkIdx(@RequestBody ToDoList toDoList){
        return replyService.checkTodo(toDoList.getIdx());
    }

    @PostMapping
    public ResponseEntity<?> postReply(@Valid @RequestBody ReplyDto replyDto, Errors errors){
        return replyService.postReply(replyDto, errors);
    }

    @PutMapping("/{idx}")
    public ResponseEntity<?> putReply(@PathVariable("idx")Long idx, @Valid @RequestBody ReplyDto replyDto, Errors errors){
        return replyService.putReply(idx, replyDto.getContent(), errors);
    }

    @DeleteMapping("/{idx}")
    public ResponseEntity<?> deleteReply(@PathVariable("idx") Long idx){
        return replyService.deleteReply(idx);
    }

}
