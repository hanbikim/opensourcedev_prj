package com.opendev3.devjournal.controller;

import com.opendev3.devjournal.domain.entity.User;
import com.opendev3.devjournal.dto.ToDoListDto;
import com.opendev3.devjournal.domain.repository.UserRepository;
import com.opendev3.devjournal.service.ToDoListService;
import com.opendev3.devjournal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
@Slf4j
public class ToDoListController {

    private final ToDoListService toDoListService;

    private final UserRepository userRepository;

    private User user;

    @GetMapping("/list")
    public String list(Model model) {
        this.user = userRepository.findById(UserService.currentUserId());
        model.addAttribute("tdlList", toDoListService.findTdlListByUser(this.user));
        return "/toDoList/listTest";
    }

    //tdlList add
    @PostMapping
    public ResponseEntity<?> saveTDL(@RequestBody ToDoListDto toDoListDto){
        return this.toDoListService.saveTdl(toDoListDto, this.user);
    }

    //tdlList update
    @PutMapping("/{idx}")
    public ResponseEntity<?> putTDL(@PathVariable("idx")Long idx, @RequestBody ToDoListDto toDoListDto){
        return this.toDoListService.updateTDL(idx, toDoListDto);
    }

    //tdlList 완료여부 update
    @PutMapping("/status/{idx}")
    public ResponseEntity<?> putTDL(@PathVariable("idx")Long idx){
        return toDoListService.updateTdlStatus(idx);
    }

    //tdlList 전체삭제
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteTDL(){
        return toDoListService.deleteAllTdl();
    }

    //tdlList 개별삭제
    @DeleteMapping("/{idx}")
    public ResponseEntity<?> delete(@PathVariable Long idx){
        return toDoListService.deleteTdl(idx);
    }

}
