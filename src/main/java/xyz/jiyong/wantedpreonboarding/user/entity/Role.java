package xyz.jiyong.wantedpreonboarding.user.entity;

import lombok.Getter;

@Getter
public enum Role {
    ENTERPRISE_USER("기업 회원"),
    PERSONAL_USER("일반 회원");

    private final String userLabel;

    Role(String userLabel) {
        this.userLabel = userLabel;
    }
}
