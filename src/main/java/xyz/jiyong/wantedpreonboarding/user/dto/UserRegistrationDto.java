package xyz.jiyong.wantedpreonboarding.user.dto;

import lombok.Builder;
import xyz.jiyong.wantedpreonboarding.user.entity.PersonalUser;
import xyz.jiyong.wantedpreonboarding.user.entity.User;

@Builder
public record UserRegistrationDto(
        String email,
        String password,
        String name
) {
    public static PersonalUser toEntity(UserRegistrationDto userRegistrationDto) {
        return PersonalUser.builder()
                .email(userRegistrationDto.email())
                .password(userRegistrationDto.password())
                .name(userRegistrationDto.name())
                .build();
    }
}
