package xyz.jiyong.wantedpreonboarding.user.controller;

import org.springframework.web.bind.annotation.*;
import xyz.jiyong.wantedpreonboarding.user.dto.UserRegistrationDto;
import xyz.jiyong.wantedpreonboarding.user.dto.UserDto;
import xyz.jiyong.wantedpreonboarding.user.dto.UserLoginDto;
import xyz.jiyong.wantedpreonboarding.user.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public void createEnterpriseUser(
            @RequestBody UserRegistrationDto userRegistrationDto) {
        userService.join(userRegistrationDto);
    }

    @PostMapping("/login")
    public UserDto login(
            @RequestBody UserLoginDto user) {
        return userService.login(user);
    }
}
