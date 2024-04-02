package xyz.jiyong.wantedpreonboarding.user.dto;

import xyz.jiyong.wantedpreonboarding.user.entity.EnterpriseUser;

public record EnterpriseUserRegistrationDto(
        String email,
        String password,
        String name,
        String country,
        String region
) {
    public static EnterpriseUser toEntity(EnterpriseUserRegistrationDto enterpriseUserRegistrationDto) {
        return EnterpriseUser.builder()
                .email(enterpriseUserRegistrationDto.email())
                .password(enterpriseUserRegistrationDto.password())
                .name(enterpriseUserRegistrationDto.name())
                .country(enterpriseUserRegistrationDto.country())
                .region(enterpriseUserRegistrationDto.region())
                .build();
    }
}
