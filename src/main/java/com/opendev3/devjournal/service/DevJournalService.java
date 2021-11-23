package com.opendev3.devjournal.service;

import com.opendev3.devjournal.domain.entity.DevJournalEntity;
import com.opendev3.devjournal.dto.DevJournalDto;
import com.opendev3.devjournal.domain.repository.DevJournalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DevJournalService {
    private DevJournalRepository devjournalRepository;

    @Transactional
    public List<DevJournalDto> getDevJournallist() {
        List<DevJournalEntity> devjournalEntities = devjournalRepository.findAll();
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
    public Long savePost(DevJournalDto devjournalDto) {
        return devjournalRepository.save(devjournalDto.toEntity()).getTidyid();
    }

    @Transactional
    public void deletePost(Long tidyid) {

        devjournalRepository.deleteById(tidyid);
    }
}
