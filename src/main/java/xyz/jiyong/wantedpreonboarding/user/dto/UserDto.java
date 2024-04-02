package xyz.jiyong.wantedpreonboarding.user.dto;

import lombok.Builder;
import xyz.jiyong.wantedpreonboarding.user.entity.User;

@Builder
public record UserDto(
        long id,
        String email,
        String name
) {
    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
