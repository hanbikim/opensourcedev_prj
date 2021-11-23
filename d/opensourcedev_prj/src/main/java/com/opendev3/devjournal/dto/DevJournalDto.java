package com.opendev3.devjournal.dto;

import com.opendev3.devjournal.domain.entity.DevJournalEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DevJournalDto {
    private Long id;
    private String title;
    private String emotion;
    private String curiosity;
    private String improvement;
    private String problem;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public DevJournalEntity toEntity(){
        DevJournalEntity devjournalEntity = DevJournalEntity.builder()
                .id(id)
                .title(title)
                .emotion(emotion)
                .curiosity(curiosity)
                .improvement(improvement)
                .problem(problem)
                .build();
        return devjournalEntity;
    }

    @Builder
    public DevJournalDto(Long id, String title, String curiosity, String emotion, String improvement, String problem,
                          LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.emotion = emotion;
        this.curiosity = curiosity;
        this.improvement = improvement;
        this.problem = problem;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

}
