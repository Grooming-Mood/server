package com.be.grooming_mood.reaction.controller;



import com.be.grooming_mood.reaction.dto.ReactionDeleteDto;
import com.be.grooming_mood.reaction.dto.ReactionDto;
import com.be.grooming_mood.reaction.service.ReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reaction")
public class ReactionController {

    private final ReactionService reactionService;

    @PostMapping("")
    public Integer createReaction(@Valid @RequestBody ReactionDto reactionDto) {

        return reactionService.createReaction(
                reactionDto.getUserId(),
                reactionDto.getDiaryId(),
                reactionDto.getReaction());
    }

    @DeleteMapping("")
    public Integer cancelReaction(@Valid @RequestBody ReactionDeleteDto reactionDto) {

        return reactionService.deleteReaction(
                reactionDto.getUserId(),
                reactionDto.getDiaryId());
    }

}
