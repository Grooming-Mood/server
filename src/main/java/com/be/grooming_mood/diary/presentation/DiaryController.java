package com.be.grooming_mood.diary.presentation;

import com.be.grooming_mood.diary.application.DiaryCommandService;
import com.be.grooming_mood.diary.presentation.dto.DiaryCreateDto;
import com.be.grooming_mood.diary.presentation.dto.DiaryDtoMapper;
import com.be.grooming_mood.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DiaryController {

    private final UserRepository userRepository;
    private final DiaryCommandService diaryCommandService;
    private final DiaryDtoMapper diaryDtoMapper;

    @PostMapping("/{userId}")
    public long createDiary(@PathVariable("userId") Long userId, @RequestBody DiaryCreateDto diaryCreateDto){
        return diaryCommandService.create(userId, diaryDtoMapper.toCreateCommand(diaryCreateDto));
    }
}