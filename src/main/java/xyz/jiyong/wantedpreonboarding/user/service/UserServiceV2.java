package xyz.jiyong.wantedpreonboarding.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import xyz.jiyong.wantedpreonboarding.global.dto.JwtToken;
import xyz.jiyong.wantedpreonboarding.global.security.JwtTokenProvider;
import xyz.jiyong.wantedpreonboarding.user.dto.UserLoginDto;

@Service
@RequiredArgsConstructor
public class UserServiceV2 {

    private final CustomUserDetailsService customUserDetailsService;
    private final DaoAuthenticationProvider authenticationProvider;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtToken login(UserLoginDto userLoginDto) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userLoginDto.email());

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, userLoginDto.password(), userDetails.getAuthorities());

        Authentication authentication =
                authenticationProvider.authenticate(authenticationToken);

        return jwtTokenProvider.generateToken(authentication);
    }
}
