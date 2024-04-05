package xyz.jiyong.wantedpreonboarding.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import xyz.jiyong.wantedpreonboarding.global.exception.CustomErrorCode;
import xyz.jiyong.wantedpreonboarding.global.exception.CustomException;
import xyz.jiyong.wantedpreonboarding.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_ERROR));
    }
}
