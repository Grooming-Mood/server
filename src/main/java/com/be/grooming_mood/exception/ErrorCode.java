package com.be.grooming_mood.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DIARY_NOT_FOUND(NOT_FOUND, "해당 다이어리를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}