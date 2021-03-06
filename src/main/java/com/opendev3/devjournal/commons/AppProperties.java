package com.opendev3.devjournal.commons;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app-properties")
@Getter @Setter
public class AppProperties {

    private String username;
    private String password;
    private String email;
}
