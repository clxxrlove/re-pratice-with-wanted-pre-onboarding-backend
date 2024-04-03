package xyz.jiyong.wantedpreonboarding.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.jiyong.wantedpreonboarding.global.response.CustomErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ CustomException.class })
    protected ResponseEntity<CustomErrorResponse> handleException(CustomException e) {
        return CustomErrorResponse.toResponseEntity(e.getCustomErrorCode());
    }
}
