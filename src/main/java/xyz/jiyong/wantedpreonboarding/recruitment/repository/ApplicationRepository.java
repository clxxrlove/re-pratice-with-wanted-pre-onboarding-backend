package xyz.jiyong.wantedpreonboarding.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.jiyong.wantedpreonboarding.recruitment.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
