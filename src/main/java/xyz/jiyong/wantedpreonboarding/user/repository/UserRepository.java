package xyz.jiyong.wantedpreonboarding.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.jiyong.wantedpreonboarding.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
