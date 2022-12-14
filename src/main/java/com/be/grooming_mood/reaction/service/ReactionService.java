package com.be.grooming_mood.reaction.service;


import com.be.grooming_mood.diary.domain.Diary;
import com.be.grooming_mood.diary.domain.DiaryJpaInterfaceRepository;
import com.be.grooming_mood.exception.ErrorCode;
import com.be.grooming_mood.exception.NotFoundException;
import com.be.grooming_mood.reaction.domain.Reaction;
import com.be.grooming_mood.reaction.domain.ReactionType;
import com.be.grooming_mood.reaction.repository.ReactionRepository;
import com.be.grooming_mood.user.domain.User;
import com.be.grooming_mood.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReactionService {
    private final ReactionRepository reactionRepository;
    private final UserRepository userRepository;
    private final DiaryJpaInterfaceRepository diaryRepository;

    @Transactional
    public Integer createReaction(Long userId,
                               Long diaryId,
                               ReactionType reactionType) {
        User user = validateUser(userId);
        Diary diary = validateDiary(diaryId);



        Reaction reaction = Reaction.builder()
                .user(user)
                .diary(diary)
                .reaction(reactionType)
                .createdDate(LocalDateTime.now())
                .build();

        reactionRepository.save(reaction);

        Integer reactionCnt = reactionRepository.countByDiaryId(diaryId);
        return reactionCnt;
    }


    @Transactional
    public Integer deleteReaction(Long userId,
                                  Long diaryId) {
        User user = validateUser(userId);
        Diary diary = validateDiary(diaryId);



        reactionRepository.deleteAllByDiaryIdAndUserId(diaryId, userId);

        return reactionRepository.countByDiaryId(diaryId);
    }

    private User validateUser(Long userId) {
        Optional<User> userCheck = userRepository.findById(userId);

        User user = userCheck.orElseThrow(() ->
                new NotFoundException(ErrorCode.USER_NOT_FOUND));

        return user;
    }

    private Diary validateDiary(Long diaryId) {
        Optional<Diary> diaryCheck = diaryRepository.findById(diaryId);

        Diary diary = diaryCheck.orElseThrow(() ->
                new NotFoundException(ErrorCode.DIARY_NOT_FOUND));

        return diary;
    }

}
