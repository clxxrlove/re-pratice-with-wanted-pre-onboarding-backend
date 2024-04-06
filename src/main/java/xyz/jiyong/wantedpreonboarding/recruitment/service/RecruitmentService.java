package xyz.jiyong.wantedpreonboarding.recruitment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import xyz.jiyong.wantedpreonboarding.global.exception.CustomErrorCode;
import xyz.jiyong.wantedpreonboarding.global.exception.CustomException;
import xyz.jiyong.wantedpreonboarding.recruitment.dto.RecruitmentDto;
import xyz.jiyong.wantedpreonboarding.recruitment.entity.Recruitment;
import xyz.jiyong.wantedpreonboarding.recruitment.repository.RecruitmentRepository;
import xyz.jiyong.wantedpreonboarding.user.entity.EnterpriseUser;
import xyz.jiyong.wantedpreonboarding.user.repository.EnterpriseUserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final EnterpriseUserRepository enterpriseUserRepository;

    public RecruitmentDto getRecruitment(long id) {
        return RecruitmentDto.from(getRecruitmentFromId(id));
    }

    public List<RecruitmentDto> getRecruitments(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return RecruitmentDto.fromPageable(recruitmentRepository.findAllByOrderByIdDesc(pageRequest));
    }

    public RecruitmentDto createRecruitment(RecruitmentDto recruitmentDto) {
        EnterpriseUser enterpriseUser = getCurrentUserFromAuthentication();
        Recruitment recruitment = Recruitment.builder()
                .enterpriseId(enterpriseUser)
                .position(recruitmentDto.position())
                .guarantee(recruitmentDto.guarantee())
                .content(recruitmentDto.content())
                .techStack(recruitmentDto.techStack())
                .build();

        return RecruitmentDto.from(recruitmentRepository.save(recruitment));
    }

    public RecruitmentDto modifyRecruitment(RecruitmentDto recruitmentDto, long id) {
        Recruitment recruitment = getRecruitmentFromId(id);

        recruitment.updatePartially(recruitmentDto);
        return RecruitmentDto.from(recruitmentRepository.save(recruitment));
    }

    public void destroyRecruitment(long id) {
        recruitmentRepository.delete(getRecruitmentFromId(id));
    }

    private EnterpriseUser getCurrentUserFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userEmail = userDetails.getUsername();

        return enterpriseUserRepository.findByEmail(userEmail)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_ERROR));
    }

    private Recruitment getRecruitmentFromId(long id) {
        return recruitmentRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_ERROR));
    }
}
