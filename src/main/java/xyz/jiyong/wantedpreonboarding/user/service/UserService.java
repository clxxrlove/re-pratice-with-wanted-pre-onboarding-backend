package xyz.jiyong.wantedpreonboarding.user.service;

import xyz.jiyong.wantedpreonboarding.global.exception.CustomErrorCode;
import xyz.jiyong.wantedpreonboarding.global.exception.CustomException;
import xyz.jiyong.wantedpreonboarding.user.dto.EnterpriseUserRegistrationDto;
import xyz.jiyong.wantedpreonboarding.user.dto.UserDto;
import xyz.jiyong.wantedpreonboarding.user.dto.UserRegistrationDto;
import xyz.jiyong.wantedpreonboarding.user.entity.PersonalUser;
import xyz.jiyong.wantedpreonboarding.user.entity.User;
import xyz.jiyong.wantedpreonboarding.user.repository.EnterpriseUserRepository;
import xyz.jiyong.wantedpreonboarding.user.repository.PersonalUserRepository;
import xyz.jiyong.wantedpreonboarding.user.repository.UserRepository;

public class UserService {

    private final EnterpriseUserRepository enterpriseUserRepository;
    private final PersonalUserRepository personalUserRepository;
    private final UserRepository userRepository;

    public UserService(EnterpriseUserRepository enterpriseUserRepository, PersonalUserRepository personalUserRepository, UserRepository userRepository) {
        this.enterpriseUserRepository = enterpriseUserRepository;
        this.personalUserRepository = personalUserRepository;
        this.userRepository = userRepository;
    }

    public void joinPersonalUser(UserRegistrationDto userRegistrationDto) {
        personalUserRepository.save(UserRegistrationDto.toEntity(userRegistrationDto));
    }

    public void joinEnterpriseUser(EnterpriseUserRegistrationDto enterpriseUserRegistrationDto) {
        enterpriseUserRepository.save(EnterpriseUserRegistrationDto.toEntity(enterpriseUserRegistrationDto));
    }

    public UserDto getUser(long id) {
        return UserDto.from(userRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_ERROR)));
    }
}
