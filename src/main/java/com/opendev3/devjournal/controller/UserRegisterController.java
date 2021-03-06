package com.opendev3.devjournal.controller;

import com.opendev3.devjournal.domain.entity.User;
import com.opendev3.devjournal.dto.UserDTO;
import com.opendev3.devjournal.domain.repository.UserRepository;
import com.opendev3.devjournal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
@Slf4j
public class UserRegisterController {

    private final UserRepository userRepository;

    private final UserService userService;

    @GetMapping
    public String register() {
        return "/member/signup";
    }
    @PostMapping("/checkEmail")
    public ResponseEntity<?> emailCheck(@RequestBody User user){
        log.info("EMAIL 중복검사 진입");

        User emailCheckUser = userRepository.findByEmail(user.getEmail());

        if(emailCheckUser == null) {
            return new ResponseEntity<>("{}", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("email이 중복입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/idCheck")
    public ResponseEntity<?> idCheck(@RequestBody User user){
        User registerUser = userRepository.findById(user.getId());

        if(registerUser == null) {
            return new ResponseEntity<>("{}", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("id가 중복입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> postUser(@Valid @RequestBody UserDTO userDTO, Errors errors){
        log.info("회원가입 진입");
        if(errors.hasErrors()){
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        else {
            userService.saveUser(userDTO);
            return new ResponseEntity<>("{}", HttpStatus.CREATED);
        }

    }
}
