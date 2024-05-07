package com.nuri.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 에러 발생"),
    INVALID_JOB_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 공고"),
    INVALID_EDU_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 교육")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
