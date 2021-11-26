package com.opendev3.devjournal.domain.repository;

import com.opendev3.devjournal.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(String id);
    User findByPassword(String password);
    User findByEmail(String email);
}
