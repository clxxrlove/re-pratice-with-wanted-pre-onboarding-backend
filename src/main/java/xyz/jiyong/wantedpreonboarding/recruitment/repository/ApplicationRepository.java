package xyz.jiyong.wantedpreonboarding.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.jiyong.wantedpreonboarding.recruitment.entity.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
