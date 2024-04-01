package xyz.jiyong.wantedpreonboarding.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.jiyong.wantedpreonboarding.user.entity.PersonalUser;

@Repository
public interface PersonalUserRepository extends JpaRepository<PersonalUser, Long> {
}
