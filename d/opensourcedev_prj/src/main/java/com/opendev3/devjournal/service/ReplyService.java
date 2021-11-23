package com.opendev3.devjournal.service;

import com.opendev3.devjournal.domain.entity.Reply;
import com.opendev3.devjournal.domain.entity.ToDoList;
import com.opendev3.devjournal.dto.ReplyDto;
import com.opendev3.devjournal.domain.repository.ReplyRepository;
import com.opendev3.devjournal.domain.repository.ToDoListRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    private final ToDoListRepository toDoListRepository;
    
    private final ModelMapper modelMapper;

    private ToDoList toDoList;

    public ResponseEntity<?> checkTodo(Long idx){
        this.toDoList = toDoListRepository.findToDoListByIdx(idx);
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    public ResponseEntity<?> postReply(ReplyDto replyDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        Reply reply = modelMapper.map(replyDto, Reply.class);
        reply.addTodo(this.toDoList);
        reply.setCreatedDate(LocalDateTime.now());
        Reply savedReply = replyRepository.save(reply);

        return new ResponseEntity<>(savedReply, HttpStatus.CREATED);
    }

    public ResponseEntity<?> putReply(Long idx, String modified, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        Optional<Reply> replyById = replyRepository.findById(idx);
        if (!replyById.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Reply reply = replyById.get();
        reply.update(modified);
        replyRepository.save(reply);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteReply(Long idx) {
        if (!replyRepository.findById(idx).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        replyRepository.deleteById(idx);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
