package com.opendev3.devjournal.service;

import com.opendev3.devjournal.domain.entity.DevJournalEntity;
import com.opendev3.devjournal.domain.entity.MeetingEntity;
import com.opendev3.devjournal.domain.repository.DevJournalRepository;
import com.opendev3.devjournal.dto.DevJournalDto;
import com.opendev3.devjournal.dto.MeetingDto;
import com.opendev3.devjournal.domain.repository.MeetingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MeetingService {
    private MeetingRepository meetingRepository;

    @Transactional
    public List<MeetingDto> getMeetinglist() {
        List<MeetingEntity> meetingEntities = meetingRepository.findAll();
        List<MeetingDto> meetingDtoList = new ArrayList<>();

        for (MeetingEntity meetingEntity : meetingEntities) {
            MeetingDto meetingDTO = MeetingDto.builder()
                    .meetid(meetingEntity.getMeetid())
                    .mtitle(meetingEntity.getMtitle())
                    .place(meetingEntity.getPlace())
                    .people(meetingEntity.getPeople())
                    .purpose(meetingEntity.getPurpose())
                    .pre(meetingEntity.getPre())
                    .mcontent(meetingEntity.getMcontent())
                    .post(meetingEntity.getPost())
                    .createdDate(meetingEntity.getCreatedDate())
                    .build();

            meetingDtoList.add(meetingDTO);
        }

        return meetingDtoList;
    }


    @Transactional
    public MeetingDto getPost(Long meetid) {
        Optional<MeetingEntity> meetingEntityWrapper = meetingRepository.findById(meetid);
        MeetingEntity meetingEntity = meetingEntityWrapper.get();

        MeetingDto meetingDTO =MeetingDto.builder()
                .meetid(meetingEntity.getMeetid())
                .mtitle(meetingEntity.getMtitle())
                .place(meetingEntity.getPlace())
                .people(meetingEntity.getPeople())
                .purpose(meetingEntity.getPurpose())
                .pre(meetingEntity.getPre())
                .mcontent(meetingEntity.getMcontent())
                .post(meetingEntity.getPost())
                .createdDate(meetingEntity.getCreatedDate())
                .build();

        return meetingDTO;
    }

    @Transactional
    public Long savePost(MeetingDto meetingDto) {
        return meetingRepository.save(meetingDto.toEntity()).getMeetid();
    }

    @Transactional
    public void deletePost(Long meetid) {

        meetingRepository.deleteById(meetid);
    }


}
