package xyz.jiyong.wantedpreonboarding.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.jiyong.wantedpreonboarding.global.dto.JwtToken;
import xyz.jiyong.wantedpreonboarding.global.response.CustomResponse;
import xyz.jiyong.wantedpreonboarding.user.dto.UserLoginDto;
import xyz.jiyong.wantedpreonboarding.user.service.UserServiceV2;

@RestController
@RequestMapping("/api/v2/user")
@RequiredArgsConstructor
public class UserControllerV2 {

    private final UserServiceV2 userServiceV2;

    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody UserLoginDto userLoginDto) {
        return CustomResponse.of(HttpStatus.OK, userServiceV2.login(userLoginDto));
    }
}
