package com.nuri.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class JobInfoException extends RuntimeException {

    private ErrorCode errorCode;
    private HttpStatus status;
    private String message;

    public JobInfoException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.status = errorCode.getHttpStatus();
        this.message = null;
    }

    @Override
    public String getMessage() {
        if (message == null) {
            return errorCode.getMessage();
        } else {
            return String.format("%s : %s", errorCode.getMessage(), message);
        }
    }
}
