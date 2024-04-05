package xyz.jiyong.wantedpreonboarding.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.jiyong.wantedpreonboarding.global.response.CustomResponse;
import xyz.jiyong.wantedpreonboarding.user.dto.UserRegistrationDto;
import xyz.jiyong.wantedpreonboarding.user.dto.UserDto;
import xyz.jiyong.wantedpreonboarding.user.dto.UserLoginDto;
import xyz.jiyong.wantedpreonboarding.user.service.UserServiceV1;

import java.util.Enumeration;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserControllerV1 {

    private final UserServiceV1 userServiceV1;

    @PostMapping("")
    public ResponseEntity<Void> createUser(
            @RequestBody UserRegistrationDto userRegistrationDto) {
        userServiceV1.join(userRegistrationDto);
        return CustomResponse.ofNoContent(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(
            @RequestBody UserLoginDto user) {
        return CustomResponse.of(
                HttpStatus.OK, userServiceV1.login(user));
    }

    /**
     * 추후 삭제해야 하는 API (OR 변경)
     * -> JWT 토큰 인증 방식으로 변경 시 Payload -> id 사용
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> withDraw(@PathVariable long id) {
        userServiceV1.destroyUser(id);
        return CustomResponse.ofNoContent(HttpStatus.OK);
    }

    // For check Authentication header what contains Bearer jwt token
    @GetMapping("/test")
    public ResponseEntity<Void> test(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaderNames();

        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            String headerValue = request.getHeader(headerName);
            log.info(headerName + ": " + headerValue);
        }

        return CustomResponse.ofNoContent(HttpStatus.OK);
    }
}
