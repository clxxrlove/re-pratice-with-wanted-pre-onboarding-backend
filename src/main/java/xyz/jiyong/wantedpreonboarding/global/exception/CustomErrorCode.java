package xyz.jiyong.wantedpreonboarding.global.exception;

import lombok.Getter;

@Getter
public enum CustomErrorCode {

    NOT_FOUND_ERROR("결과가 존재하지 않습니다.");

    private final String detailMessage;

    CustomErrorCode(String detailMessage) {
        this.detailMessage = detailMessage;
    }
}
