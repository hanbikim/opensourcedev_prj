package com.opendev3.devjournal.service;

import com.opendev3.devjournal.domain.entity.DevJournalEntity;
import com.opendev3.devjournal.dto.DevJournalDto;
import com.opendev3.devjournal.domain.repository.DevJournalRepository;
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
public class DevJournalService {
    private DevJournalRepository devjournalRepository;

    private static final int BLOCK_PAGE_NUM_COUNT = 5;  // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 4;       // 한 페이지에 존재하는 게시글 수


    @Transactional
    public List<DevJournalDto> getDevJournallist(Integer pageNum) {
        Page<DevJournalEntity> page = devjournalRepository.findAll(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdDate")));
        List<DevJournalEntity> devjournalEntities = page.getContent();
        List<DevJournalDto> devjournalDtoList = new ArrayList<>();

        for ( DevJournalEntity devjournalEntity : devjournalEntities) {
            DevJournalDto devjournalDTO = DevJournalDto.builder()
                    .tidyid(devjournalEntity.getTidyid())
                    .emotion(devjournalEntity.getEmotion())
                    .curiosity(devjournalEntity.getCuriosity())
                    .improvement(devjournalEntity.getImprovement())
                    .problem(devjournalEntity.getProblem())
                    .title(devjournalEntity.getTitle())
                    .createdDate(devjournalEntity.getCreatedDate())
                    .build();

            devjournalDtoList.add(devjournalDTO);
        }

        return devjournalDtoList;
    }


    @Transactional
    public DevJournalDto getPost(Long tidyid) {
        Optional<DevJournalEntity> devjournalEntityWrapper = devjournalRepository.findById(tidyid);
        DevJournalEntity devjournalEntity = devjournalEntityWrapper.get();

        DevJournalDto devjournalDTO = DevJournalDto.builder()
                .tidyid(devjournalEntity.getTidyid())
                .emotion(devjournalEntity.getEmotion())
                .curiosity(devjournalEntity.getCuriosity())
                .improvement(devjournalEntity.getImprovement())
                .problem(devjournalEntity.getProblem())
                .title(devjournalEntity.getTitle())
                .createdDate(devjournalEntity.getCreatedDate())
                .build();

        return devjournalDTO;
    }

    @Transactional
    public Long getBoardCount() {
        return devjournalRepository.count();
    }


    @Transactional
    public Long savePost(DevJournalDto devjournalDto) {
        return devjournalRepository.save(devjournalDto.toEntity()).getTidyid();
    }

    @Transactional
    public void deletePost(Long tidyid) {

        devjournalRepository.deleteById(tidyid);
    }

    @Transactional
    public List<DevJournalDto> searchPosts(String keyword) {
        List<DevJournalEntity> devjournalEntities = devjournalRepository.findByTitleContaining(keyword);
        List<DevJournalDto> devjournalDtoList = new ArrayList<>();

        if (devjournalEntities.isEmpty()) return devjournalDtoList;

        for (DevJournalEntity devjournalEntity : devjournalEntities) {
            devjournalDtoList.add(this.convertEntityToDto(devjournalEntity));
        }

        return devjournalDtoList;
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

    private DevJournalDto convertEntityToDto(DevJournalEntity devjournalEntity) {
        return DevJournalDto.builder()
                .tidyid(devjournalEntity.getTidyid())
                .emotion(devjournalEntity.getEmotion())
                .curiosity(devjournalEntity.getCuriosity())
                .improvement(devjournalEntity.getImprovement())
                .problem(devjournalEntity.getProblem())
                .title(devjournalEntity.getTitle())
                .createdDate(devjournalEntity.getCreatedDate())
                .build();
    }
}
