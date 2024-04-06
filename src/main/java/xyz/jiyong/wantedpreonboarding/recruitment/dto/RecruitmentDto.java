package xyz.jiyong.wantedpreonboarding.recruitment.dto;

import lombok.Builder;
import org.springframework.data.domain.Page;
import xyz.jiyong.wantedpreonboarding.recruitment.entity.Recruitment;
import xyz.jiyong.wantedpreonboarding.user.entity.EnterpriseUser;

import java.util.List;

@Builder
public record RecruitmentDto(
        String position,
        long guarantee,
        String content,
        String techStack
) {
    public static RecruitmentDto from(Recruitment recruitment) {
        return RecruitmentDto.builder()
                .position(recruitment.getPosition())
                .guarantee(recruitment.getGuarantee())
                .content(recruitment.getContent())
                .techStack(recruitment.getTechStack())
                .build();
    }

    public static List<RecruitmentDto> fromPageable(Page<Recruitment> recruitments) {
        return recruitments
                .map(RecruitmentDto::from)
                .stream().toList();
    }

    public static Recruitment toEntity(RecruitmentDto recruitmentDto, EnterpriseUser enterpriseUser) {
        return Recruitment.builder()
                .enterpriseId(enterpriseUser)
                .position(recruitmentDto.position())
                .guarantee(recruitmentDto.guarantee())
                .content(recruitmentDto.content())
                .techStack(recruitmentDto.techStack())
                .build();
    }
}
