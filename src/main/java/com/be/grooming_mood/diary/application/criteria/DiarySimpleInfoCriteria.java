package com.be.grooming_mood.diary.application.criteria;

import com.be.grooming_mood.feeling.domain.FeelingType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DiarySimpleInfoCriteria {
    private String userName;
    private String profileImg;
    private Long diaryId;
    private String diaryContent;
    private FeelingType feeling;

    @QueryProjection @Builder
    public DiarySimpleInfoCriteria(Long diaryId, String diaryContent, String userName, String profileImg,FeelingType feeling){
        this.diaryId = diaryId;
        this.diaryContent = diaryContent;
        this.userName = userName;
        this.profileImg = profileImg;
        this.feeling = feeling;
    }
}
