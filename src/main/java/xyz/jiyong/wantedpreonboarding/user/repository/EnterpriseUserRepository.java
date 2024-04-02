package xyz.jiyong.wantedpreonboarding.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.jiyong.wantedpreonboarding.user.entity.EnterpriseUser;

@Repository
public interface EnterpriseUserRepository extends JpaRepository<EnterpriseUser, Long> {
}
