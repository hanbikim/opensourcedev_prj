package com.opendev3.devjournal.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "meeting")
public class MeetingEntity extends TimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long meetid;

    @Column(length = 100, nullable = false)
    private String mtitle;

    @Column(length = 100, nullable = false)
    private String mprjTitle;

    @Column(length = 100, nullable = false)
    private String place;

    @Column(length = 100, nullable = false)
    private String people;

    @Column(length = 100, nullable = false)
    private String purpose;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String pre;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String mcontent;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String post;


    @Builder
    public MeetingEntity(Long meetid, String mprjTitle, String mtitle, String place,String people, String purpose,
                         String pre, String mcontent, String post) {
        this.meetid = meetid;
        this.mtitle = mtitle;
        this.mprjTitle=mprjTitle;
        this.place = place;
        this.people = people;
        this.purpose = purpose;
        this.pre = pre;
        this.mcontent=mcontent;
        this.post= post;
    }

}
