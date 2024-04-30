package xyz.jiyong.wantedpreonboarding.global.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.jiyong.wantedpreonboarding.global.response.CustomErrorResponse;

import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ CustomException.class })
    protected ResponseEntity<CustomErrorResponse> handleCustomException(CustomException e) {
        return CustomErrorResponse.toResponseEntityFromCustomCode(e.getCustomErrorCode());
    }

    /**
     * Spring Security Error:
     *  -> JwtAuthenticationFilter, UsernamePasswordAuthenticationFilter
     * */
    @ExceptionHandler({ AccessDeniedException.class, AuthenticationException.class })
    protected ResponseEntity<CustomErrorResponse> handleAuthenticationException() {
        return CustomErrorResponse.toResponseEntityFromCustomCode(CustomErrorCode.UNAUTHORIZED);
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    protected ResponseEntity<CustomErrorResponse> handleIllegalArgumentException() {
        return CustomErrorResponse.toResponseEntityFromCustomCode(CustomErrorCode.BAD_CREDENTIAL);
    }

    @ExceptionHandler({ ExpiredJwtException.class, MalformedJwtException.class, SignatureException.class })
    protected ResponseEntity<CustomErrorResponse> handleJwtSignatureException() {
        return CustomErrorResponse.toResponseEntityFromCustomCode(CustomErrorCode.TOKEN_SIGNATURE_ERROR);
    }

    @ExceptionHandler({ BadCredentialsException.class, InternalAuthenticationServiceException.class, CredentialsExpiredException.class })
    protected ResponseEntity<CustomErrorResponse> handleUsernamePasswordException() {
        return CustomErrorResponse.toResponseEntityFromCustomCode(CustomErrorCode.BAD_CREDENTIAL);
    }

    @ExceptionHandler({ DisabledException.class, AccountExpiredException.class, LockedException.class })
    protected ResponseEntity<CustomErrorResponse> handleDisabledUserException() {
        return CustomErrorResponse.toResponseEntityFromCustomCode(CustomErrorCode.DISABLED_USER_ERROR);
    }

    // 짬통 Exception Handler -> 위에서 규정한 Exception 제외한 모든 에러 Controller
    @ExceptionHandler({ Exception.class })
    protected ResponseEntity<Map<String, String>> handleGeneralException(Exception e) {
        log.error("Exception: " + e.getClass());
        log.error(Arrays.toString(e.getStackTrace()));

        HashMap<String, String> errorMap = new HashMap<>();
        errorMap.put("statusMessage", "Error occurred.");

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMap);
    }
}
