package com.be.grooming_mood.feeling.controller;

import com.be.grooming_mood.feeling.domain.FeelingHistory;
import com.be.grooming_mood.feeling.dto.FeelingHistoryInfo;
import com.be.grooming_mood.feeling.dto.FeelingHistoryInfoList;
import com.be.grooming_mood.feeling.service.FeelingHistoryService;
import com.be.grooming_mood.feeling.dto.FeelingHistoryCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feeling-history")
public class FeelingHistoryController {

    private final FeelingHistoryService feelingHistoryService;


    @GetMapping("/userId/{userId}")
    public FeelingHistoryInfoList getFeelingHistoryInMonth(@PathVariable("userId") Long userId) {
        return feelingHistoryService.getFeelingHistoryList(userId);
    }

    @PostMapping("")
    public String createFeelingHistory(@Valid @RequestBody FeelingHistoryCreateDto feelingHistoryCreateDto) {
        return feelingHistoryService.createFeelingHistory(feelingHistoryCreateDto);
    }
}
