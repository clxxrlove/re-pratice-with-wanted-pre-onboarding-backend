package xyz.jiyong.wantedpreonboarding.recruitment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.jiyong.wantedpreonboarding.recruitment.entity.Recruitment;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    Page<Recruitment> findAllByOrderByIdDesc(Pageable pageable);
}
