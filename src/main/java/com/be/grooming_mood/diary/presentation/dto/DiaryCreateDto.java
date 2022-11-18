package com.be.grooming_mood.diary.presentation.dto;

import com.be.grooming_mood.feeling.domain.FeelingType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryCreateDto{

    private FeelingType feeling;
    private String diaryContent;
    private Boolean isPublic;

    @Builder
    public DiaryCreateDto(FeelingType feeling,
                          String diaryContent,
                          Boolean isPublic) {
        this.feeling = feeling;
        this.diaryContent = diaryContent;
        this.isPublic = isPublic;

    }
}
