package com.be.grooming_mood.diary.presentation;

import com.be.grooming_mood.diary.application.DiaryCommandService;
import com.be.grooming_mood.diary.application.DiaryQueryService;
import com.be.grooming_mood.diary.application.command.DiaryUpdateCommand;
import com.be.grooming_mood.diary.application.criteria.DiaryDetailInfoCriteria;
import com.be.grooming_mood.diary.application.criteria.DiaryListQueryPagingResult;
import com.be.grooming_mood.diary.application.criteria.DiaryListQueryResult;
import com.be.grooming_mood.diary.presentation.dto.DiaryCreateDto;
import com.be.grooming_mood.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/my-diary")
public class MyDiaryController {

    private final DiaryQueryService diaryQueryService;
    private final DiaryCommandService diaryCommandService;

    @CrossOrigin(origins = "*")
    @ApiOperation(value = "일기 등록")
    @PostMapping("/{userId}")
    public Long createDiary(@PathVariable("userId") Long userId,
                            @Valid @RequestBody DiaryCreateDto diaryCreateDto){
        return diaryCommandService.create(userId, diaryCreateDto);
    }

    @CrossOrigin(origins = "*")
    @ApiOperation(value ="일기 상세 보기")
    @GetMapping("/{diaryId}/diary-detail")
    public DiaryDetailInfoCriteria getDiaryInfo(@PathVariable Long diaryId){
        return diaryQueryService.findDetailInfo(diaryId);
    }

    @CrossOrigin(origins = "*")
    @ApiOperation(value = "일기 수정")
    @PostMapping("/{userId}/{diaryId}")
    public void updateDiary(@PathVariable("diaryId") Long diaryId,
                            @Valid DiaryUpdateCommand diaryUpdateCommand){
        diaryCommandService.update(diaryId,diaryUpdateCommand);
    }

    @CrossOrigin(origins = "*")
    @ApiOperation(value = "일기 삭제")
    @DeleteMapping("/{userId}/{diaryId}")
    public void deleteDiary(@PathVariable("diaryId") Long diaryId){
        diaryCommandService.delete(diaryId);
    }

    @CrossOrigin(origins = "*")
    @ApiOperation(value ="내 일기 리스트")
    @GetMapping("/{userId}")
    public DiaryListQueryPagingResult getMyDiaryList(@PathVariable("userId") Long userId,
                                                     @RequestParam(required = false) String cursor,
                                                     @RequestParam(required = false, defaultValue = "3") int size){
        return diaryQueryService.findMyDiaryList(userId,cursor,size);
    }

    /**
     * 스크롤
     */
//    @ApiOperation(value ="내 일기 리스트")
//    @GetMapping("/{userId}")
//    public DiaryListQueryResult getMyDiaryList(@PathVariable("userId") Long userId){
//        return diaryQueryService.findMyDiaryList(userId);
//    }

}
