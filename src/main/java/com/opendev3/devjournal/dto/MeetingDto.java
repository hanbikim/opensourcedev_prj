package com.opendev3.devjournal.dto;

import com.opendev3.devjournal.domain.entity.DevJournalEntity;
import com.opendev3.devjournal.domain.entity.MeetingEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MeetingDto {
    private Long meetid;
    private String mtitle;
    private String mprjTitle;
    private String place;
    private String people;
    private String purpose;
    private String pre;
    private String mcontent;
    private String post;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public MeetingEntity toEntity(){
        MeetingEntity meetingEntity = MeetingEntity.builder()
                .meetid(meetid)
                .mtitle(mtitle)
                .mprjTitle(mprjTitle)
                .place(place)
                .people(people)
                .purpose(purpose)
                .pre(pre)
                .mcontent(mcontent)
                .post(post)
                .build();
        return meetingEntity;
    }

    @Builder
    public MeetingDto(Long meetid, String mprjTitle, String mtitle, String place,String people, String purpose, String pre,
                      String mcontent, String post, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.meetid = meetid;
        this.mprjTitle=mprjTitle;
        this.mtitle = mtitle;
        this.place = place;
        this.people = people;
        this.purpose = purpose;
        this.pre = pre;
        this.mcontent=mcontent;
        this.post= post;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

}
