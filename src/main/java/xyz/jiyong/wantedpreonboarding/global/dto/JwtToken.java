package xyz.jiyong.wantedpreonboarding.global.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtToken {

    private String grantType;
    private String accessToken;
    private String refreshToken;

    public JwtToken(String grantType, String accessToken, String refreshToken) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
