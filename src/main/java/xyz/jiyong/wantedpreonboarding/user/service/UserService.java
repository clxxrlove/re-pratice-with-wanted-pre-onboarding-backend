package xyz.jiyong.wantedpreonboarding.user.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.jiyong.wantedpreonboarding.global.exception.CustomErrorCode;
import xyz.jiyong.wantedpreonboarding.global.exception.CustomException;
import xyz.jiyong.wantedpreonboarding.user.dto.UserRegistrationDto;
import xyz.jiyong.wantedpreonboarding.user.dto.UserDto;
import xyz.jiyong.wantedpreonboarding.user.dto.UserLoginDto;
import xyz.jiyong.wantedpreonboarding.user.entity.User;
import xyz.jiyong.wantedpreonboarding.user.repository.EnterpriseUserRepository;
import xyz.jiyong.wantedpreonboarding.user.repository.PersonalUserRepository;
import xyz.jiyong.wantedpreonboarding.user.repository.UserRepository;

@Service
public class UserService {

    private final EnterpriseUserRepository enterpriseUserRepository;
    private final PersonalUserRepository personalUserRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(EnterpriseUserRepository enterpriseUserRepository, PersonalUserRepository personalUserRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.enterpriseUserRepository = enterpriseUserRepository;
        this.personalUserRepository = personalUserRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void join(UserRegistrationDto userRegistrationDto) {
        try {
            if (userRegistrationDto.type().equals("PERSONAL"))
                personalUserRepository.save(UserRegistrationDto.toPersonalEntity(userRegistrationDto));
            else if (userRegistrationDto.type().equals("ENTERPRISE"))
                enterpriseUserRepository.save(UserRegistrationDto.toEnterpriseEntity(userRegistrationDto));
            else
                throw new CustomException(CustomErrorCode.INVALID_USER_TYPE);
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(CustomErrorCode.DATA_INTEGRITY_VIOLATION);
        }
    }

    public UserDto getUser(long id) {
        return UserDto.from(userRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_ERROR)));
    }

    public UserDto login(UserLoginDto userLoginDto) {
        User user = userRepository.findByEmail(userLoginDto.email())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_ERROR));

        if (passwordEncoder.matches(userLoginDto.password(), user.getPassword())) {
            return UserDto.from(user);
        }
        return null;
    }
}
