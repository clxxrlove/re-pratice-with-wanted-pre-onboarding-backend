package xyz.jiyong.wantedpreonboarding.recruitment.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import xyz.jiyong.wantedpreonboarding.user.entity.PersonalUser;

@Entity
@Getter
public class Application {

    @EmbeddedId
    private ApplicationId id;

    @ManyToOne
    @MapsId("recruitmentId")
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitmentId;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private PersonalUser accountId;

    @Builder
    public Application(ApplicationId id, Recruitment recruitmentId, PersonalUser accountId) {
        this.id = id;
        this.recruitmentId = recruitmentId;
        this.accountId = accountId;
    }

    protected Application() {
    }
}
