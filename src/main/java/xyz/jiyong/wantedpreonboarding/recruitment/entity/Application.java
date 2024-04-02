package xyz.jiyong.wantedpreonboarding.recruitment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import xyz.jiyong.wantedpreonboarding.user.entity.PersonalUser;

@Entity
@Getter
public class Application {

    @Id
    @ManyToOne
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitmentId;

    @Id
    @ManyToOne
    @JoinColumn(name = "account_id")
    private PersonalUser accountId;

    @Builder
    protected Application(Recruitment recruitmentId, PersonalUser accountId) {
        this.recruitmentId = recruitmentId;
        this.accountId = accountId;
    }

    protected Application() {
    }
}
