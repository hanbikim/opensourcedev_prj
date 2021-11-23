package com.opendev3.devjournal.commons;

import com.opendev3.devjournal.domain.entity.User;
import com.opendev3.devjournal.domain.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DummyData implements ApplicationRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public DummyData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.save(User.builder().id("test").password(passwordEncoder.encode("test")).email("test@gmail.com").build());
    }

}
