package xyz.jiyong.wantedpreonboarding.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.jiyong.wantedpreonboarding.recruitment.entity.Recruitment;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
}