package com.be.grooming_mood.feeling.service;

import com.be.grooming_mood.exception.NotFoundException;
import com.be.grooming_mood.feeling.domain.FeelingHistory;
import com.be.grooming_mood.feeling.dto.FeelingHistoryCreateDto;
import com.be.grooming_mood.feeling.dto.FeelingHistoryInfo;
import com.be.grooming_mood.feeling.dto.FeelingHistoryInfoList;
import com.be.grooming_mood.feeling.repository.FeelingHistoryQueryDao;
import com.be.grooming_mood.feeling.repository.FeelingHistoryRepository;
import com.be.grooming_mood.user.domain.User;
import com.be.grooming_mood.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import static com.be.grooming_mood.exception.ErrorCode.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class FeelingHistoryService {

    private final FeelingHistoryRepository feelingHistoryRepository;
    private final UserRepository userRepository;
    private final FeelingHistoryQueryDao feelingHistoryQueryDao;

    @Transactional(readOnly = true)
    public FeelingHistoryInfoList getFeelingHistoryList(Long userId){

        LocalDateTime start = YearMonth.now().atDay(1).atStartOfDay();
        LocalDateTime end = YearMonth.now().atEndOfMonth().atStartOfDay();

        // feelingHistoryRepository.findAllByCreatedDateBetween(start, end);

        return feelingHistoryQueryDao.findAllFeelingHistoryInThisMonth(start, end);
    }

    @Transactional
    public String createFeelingHistory(FeelingHistoryCreateDto feelingHistoryCreateDto) {
        Optional<User> userCheck = userRepository.findById(feelingHistoryCreateDto.getUserId());

        User user = userCheck.orElseThrow(() ->
                new NotFoundException(USER_NOT_FOUND));

        FeelingHistory feelingHistory = FeelingHistory.builder()
                .user(user)
                .feeling(feelingHistoryCreateDto.getFeeling())
                .build();

        feelingHistoryRepository.save(feelingHistory);

        return "ok";
    }
}
