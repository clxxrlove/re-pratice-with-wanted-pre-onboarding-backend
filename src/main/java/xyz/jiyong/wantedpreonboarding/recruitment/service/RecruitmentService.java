package xyz.jiyong.wantedpreonboarding.recruitment.service;

import org.springframework.stereotype.Service;
import xyz.jiyong.wantedpreonboarding.recruitment.dto.RecruitmentDto;
import xyz.jiyong.wantedpreonboarding.recruitment.repository.RecruitmentRepository;
import xyz.jiyong.wantedpreonboarding.user.repository.EnterpriseUserRepository;

@Service
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final EnterpriseUserRepository enterpriseUserRepository;

    public RecruitmentService(RecruitmentRepository recruitmentRepository, EnterpriseUserRepository enterpriseUserRepository) {
        this.recruitmentRepository = recruitmentRepository;
        this.enterpriseUserRepository = enterpriseUserRepository;
    }

//    public RecruitmentDto postRecruitment(RecruitmentDto recruitmentDto) {
//
//    }
}
