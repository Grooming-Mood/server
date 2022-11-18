package com.be.grooming_mood.diary.application.command;

import com.be.grooming_mood.feeling.domain.FeelingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class DiaryCreateCommand {
    private FeelingType feeling;
    private String diaryContent;
    private Boolean isPublic;
}
