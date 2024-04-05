package xyz.jiyong.wantedpreonboarding.global.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import xyz.jiyong.wantedpreonboarding.global.exception.CustomErrorCode;

@Builder
@Getter
@AllArgsConstructor
public class CustomErrorResponse {

    private String statusMessage;

    public static ResponseEntity<CustomErrorResponse> toResponseEntity(CustomErrorCode e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(CustomErrorResponse.builder()
                        .statusMessage(e.getDetailMessage())
                        .build()
                );
    }
}
