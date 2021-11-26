package com.opendev3.devjournal.domain.repository;

import com.opendev3.devjournal.domain.entity.Reply;
import com.opendev3.devjournal.domain.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findAllByToDoListOrderByIdx(ToDoList toDoList);

}
