package com.opendev3.devjournal.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="devjournal")

public class DevJournalEntity extends TimeEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long tidyid;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String prjTitle;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String emotion;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String curiosity;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String improvement;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String problem;

    @Builder
    public DevJournalEntity(Long tidyid, String prjTitle, String title, String emotion,String curiosity, String improvement, String problem) {
        this.tidyid = tidyid;
        this.prjTitle= prjTitle;
        this.title = title;
        this.emotion = emotion;
        this.curiosity = curiosity;
        this.improvement = improvement;
        this.problem = problem;
    }

}
