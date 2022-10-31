package com.be.grooming_mood.feeling.repository;

import com.be.grooming_mood.feeling.dto.FeelingHistoryInfo;
import com.be.grooming_mood.feeling.dto.FeelingHistoryInfoList;
import com.be.grooming_mood.feeling.dto.QFeelingHistoryInfo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.be.grooming_mood.feeling.domain.QFeelingHistory.feelingHistory;
import static com.be.grooming_mood.feeling.dto.QFeelingHistoryInfo.*;
import static com.be.grooming_mood.user.domain.QUser.user;

@RequiredArgsConstructor
@Repository
public class FeelingHistoryQueryDao {
    private final JPAQueryFactory jpaQueryFactory;

    public FeelingHistoryInfoList findAllFeelingHistoryInThisMonth(LocalDateTime start, LocalDateTime end) {
        List<FeelingHistoryInfo> infoList =
                jpaQueryFactory.select(new QFeelingHistoryInfo(feelingHistory.id, user.id, feelingHistory.feeling, feelingHistory.createdDate))
                .from(feelingHistory)
                .where(feelingHistory.createdDate.between(start, end))
                .fetch();

        return new FeelingHistoryInfoList(infoList);
    }
}
