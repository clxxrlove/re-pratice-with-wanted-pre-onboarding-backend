package xyz.jiyong.wantedpreonboarding.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public CustomErrorResponse handleException(
            CustomException e,
            HttpServletRequest request
    ) {
        return CustomErrorResponse.builder()
                .status(e.getCustomErrorCode())
                .statusMessage(e.getDetailMessage())
                .build();
    }
}
