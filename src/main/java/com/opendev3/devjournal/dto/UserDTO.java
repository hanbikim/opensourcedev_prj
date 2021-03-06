package com.opendev3.devjournal.dto;

import com.opendev3.devjournal.domain.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class UserDTO implements Serializable {

    @NotBlank(message = "아이디를 입력하세요.")
    @Size(min = 4, max = 16)
    private String id;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Size(min = 6, max = 16)
    private String password;

    @NotBlank(message = "이메일을 입력하세요.")
    @Email(message = "이메일 양식을 지키세요.", regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
    private String email;

    public User saveUser(){
        User user = new User();

        user.setId(this.id);
        user.setPassword(this.password);
        user.setEmail(this.email);
        return user;
    }

}
