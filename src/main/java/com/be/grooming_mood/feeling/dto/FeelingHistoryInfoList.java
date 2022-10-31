package com.be.grooming_mood.feeling.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class FeelingHistoryInfoList {
    private List<FeelingHistoryInfo> feelingHistoryList;

    public FeelingHistoryInfoList(List<FeelingHistoryInfo> feelingHistoryList) {
        this.feelingHistoryList = feelingHistoryList;
    }
}
