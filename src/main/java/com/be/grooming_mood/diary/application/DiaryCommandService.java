package com.be.grooming_mood.diary.application;

import com.be.grooming_mood.diary.application.command.DiaryUpdateCommand;
import com.be.grooming_mood.diary.domain.Diary;
import com.be.grooming_mood.diary.domain.DiaryJpaInterfaceRepository;
import com.be.grooming_mood.diary.presentation.dto.DiaryCreateDto;
import com.be.grooming_mood.exception.NotFoundException;
import com.be.grooming_mood.user.domain.User;
import com.be.grooming_mood.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.be.grooming_mood.exception.ErrorCode.DIARY_NOT_FOUND;
import static com.be.grooming_mood.exception.ErrorCode.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class DiaryCommandService {
    private final DiaryJpaInterfaceRepository diaryJpaInterfaceRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long create(Long userId, DiaryCreateDto diaryCreateDto){
        Optional<User> userCheck = userRepository.findById(userId);

        User user = userCheck.orElseThrow(() ->
                new NotFoundException(USER_NOT_FOUND));

        Diary diary = Diary.builder()
                .user(user)
                .feeling(diaryCreateDto.getFeeling())
                .diaryContent(diaryCreateDto.getDiaryContent())
                .isPublic(diaryCreateDto.getIsPublic())
                
                .build();
        diaryJpaInterfaceRepository.save(diary);
        return diary.getId();
    }

    @Transactional
    public void update(Long diaryId, DiaryUpdateCommand diaryUpdateCommand){
        Optional<Diary> diaryCheck = diaryJpaInterfaceRepository.findById(diaryId);
        Diary diary = diaryCheck.orElseThrow(() ->
                new NotFoundException(DIARY_NOT_FOUND));
        diary.update(diaryUpdateCommand.getDiaryContent(),diaryUpdateCommand.getIsPublic());
    }

    @Transactional
    public void delete(Long diaryId){
        diaryJpaInterfaceRepository.deleteById(diaryId);
    }
}
