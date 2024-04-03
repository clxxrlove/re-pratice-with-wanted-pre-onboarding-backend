package xyz.jiyong.wantedpreonboarding.user.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import xyz.jiyong.wantedpreonboarding.user.entity.EnterpriseUser;
import xyz.jiyong.wantedpreonboarding.user.entity.PersonalUser;

public record UserRegistrationDto(
        String email,
        String password,
        String name,
        String country,
        String region,
        String type
) {
    public static EnterpriseUser toEnterpriseEntity(UserRegistrationDto userRegistrationDto) {
        return EnterpriseUser.builder()
                .email(userRegistrationDto.email())
                .password(new BCryptPasswordEncoder().encode(userRegistrationDto.password()))
                .name(userRegistrationDto.name())
                .country(userRegistrationDto.country())
                .region(userRegistrationDto.region())
                .build();
    }

    public static PersonalUser toPersonalEntity(UserRegistrationDto userRegistrationDto) {
        return PersonalUser.builder()
                .email(userRegistrationDto.email())
                .password(new BCryptPasswordEncoder().encode(userRegistrationDto.password()))
                .name(userRegistrationDto.name())
                .build();
    }
}
