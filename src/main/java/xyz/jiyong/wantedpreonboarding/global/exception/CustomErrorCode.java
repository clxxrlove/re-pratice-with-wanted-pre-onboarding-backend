package xyz.jiyong.wantedpreonboarding.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomErrorCode {

    NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "결과가 존재하지 않습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "인증이 필요합니다."),
    INVALID_USER_TYPE(HttpStatus.BAD_REQUEST, "회원 구분이 정확하지 않습니다."),
    DATA_INTEGRITY_VIOLATION(HttpStatus.CONFLICT, "회원 정보를 다시 확인해주세요."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "제공받은 데이터에 문제가 있습니다."),
    BAD_CREDENTIAL(HttpStatus.FORBIDDEN, "인증 정보에 문제가 있습니다."),
    TOKEN_SIGNATURE_ERROR(HttpStatus.FORBIDDEN, "제공받은 토큰에 문제가 있습니다."),
    DISABLED_USER_ERROR(HttpStatus.UNAUTHORIZED, "로그인 할 수 없는 유저입니다.");

    private final HttpStatus httpStatus;
    private final String detailMessage;

    CustomErrorCode(HttpStatus httpStatus, String detailMessage) {
        this.httpStatus = httpStatus;
        this.detailMessage = detailMessage;
    }
}
