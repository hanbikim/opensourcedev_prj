package com.opendev3.devjournal.service;

import com.opendev3.devjournal.domain.entity.DevJournalEntity;
import com.opendev3.devjournal.domain.entity.MeetingEntity;
import com.opendev3.devjournal.domain.repository.DevJournalRepository;
import com.opendev3.devjournal.dto.DevJournalDto;
import com.opendev3.devjournal.dto.MeetingDto;
import com.opendev3.devjournal.domain.repository.MeetingRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MeetingService {
    private MeetingRepository meetingRepository;

    private static final int BLOCK_PAGE_NUM_COUNT = 5;  // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 4;

    @Transactional
    public List<MeetingDto> getMeetinglist(Integer pageNum) {
        Page<MeetingEntity> page = meetingRepository.findAll(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdDate")));
        List<MeetingEntity> meetingEntities = page.getContent();
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
    public Long getBoardCount() {
        return meetingRepository.count();
    }

    @Transactional
    public Long savePost(MeetingDto meetingDto) {
        return meetingRepository.save(meetingDto.toEntity()).getMeetid();
    }

    @Transactional
    public void deletePost(Long meetid) {

        meetingRepository.deleteById(meetid);
    }

    @Transactional
    public List<MeetingDto> searchPosts(String keyword) {
        List<MeetingEntity> meetingEntities = MeetingRepository.findByTitleContaining(keyword);
        List<MeetingDto> meetingDtoList = new ArrayList<>();

        if (meetingEntities.isEmpty()) return meetingDtoList;

        for (MeetingEntity meetingEntity : meetingEntities) {
            meetingDtoList.add(this.convertEntityToDto(meetingEntity));
        }

        return meetingDtoList;
    }

    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        // 총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getBoardCount());

        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

        // 페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageList[idx] = val;
        }

        return pageList;
    }

    private MeetingDto convertEntityToDto(MeetingEntity meetingEntity) {
        return MeetingDto.builder()
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
    }


}
