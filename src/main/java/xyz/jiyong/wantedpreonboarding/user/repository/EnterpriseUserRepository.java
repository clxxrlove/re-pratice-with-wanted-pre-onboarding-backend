package xyz.jiyong.wantedpreonboarding.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.jiyong.wantedpreonboarding.user.entity.EnterpriseUser;

public interface EnterpriseUserRepository extends JpaRepository<EnterpriseUser, Long> {
}
