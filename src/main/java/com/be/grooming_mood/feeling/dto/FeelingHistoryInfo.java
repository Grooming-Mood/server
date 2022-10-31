package com.be.grooming_mood.feeling.dto;

import com.be.grooming_mood.feeling.domain.FeelingType;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FeelingHistoryInfo {

    private Long feelingHistoryId;
    private Long userId;
    private FeelingType feeling;
    private LocalDateTime createdDate;

    @QueryProjection @Builder
    public FeelingHistoryInfo(
            Long feelingHistoryId,
            Long userId,
            FeelingType feeling,
            LocalDateTime createdDate) {

        this.feelingHistoryId = feelingHistoryId;
        this.userId = userId;
        this.feeling = feeling;
        this.createdDate = createdDate;
    }
}
