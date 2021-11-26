package com.opendev3.devjournal.domain.repository;

import com.opendev3.devjournal.domain.entity.ToDoList;
import com.opendev3.devjournal.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
    List<ToDoList> findAllByUserOrderByIdx(User user);
    Optional<ToDoList> findByIdx(Long idx);
    ToDoList findToDoListByIdx(Long idx);
}
