package com.opendev3.devjournal.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter @EqualsAndHashCode(of = "idx")
@Builder @NoArgsConstructor @AllArgsConstructor
@Entity
public class User implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name="user_id")
    private String id;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<ToDoList> toDoLists = new ArrayList<>();

}
