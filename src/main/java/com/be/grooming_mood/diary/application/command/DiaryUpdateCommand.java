package com.be.grooming_mood.diary.application.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@Getter
public class DiaryUpdateCommand {

    private String diaryContent;
    private Boolean isPublic;

}
