package xyz.jiyong.wantedpreonboarding.global.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Builder
@Getter
public class CustomResponse<T> {

    private HttpStatus httpStatus;
    private T data;

    public static <T> ResponseEntity<T> of(HttpStatus httpStatus, T data) {
        return ResponseEntity
                .status(httpStatus)
                .body(data);
    }

    public static ResponseEntity<Void> ofNoContent(HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).build();
    }
}