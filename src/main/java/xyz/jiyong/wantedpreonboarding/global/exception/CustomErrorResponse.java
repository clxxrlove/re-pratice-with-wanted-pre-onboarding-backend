package xyz.jiyong.wantedpreonboarding.global.exception;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class CustomErrorResponse {

    private CustomErrorCode status;
    private String statusMessage;

    public CustomErrorResponse(CustomErrorCode status, String statusMessage) {
        this.status = status;
        this.statusMessage = statusMessage;
    }
}
