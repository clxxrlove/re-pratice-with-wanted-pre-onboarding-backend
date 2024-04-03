package xyz.jiyong.wantedpreonboarding.recruitment.entity;

import jakarta.persistence.Embeddable;
import lombok.Builder;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ApplicationId implements Serializable {
    private Long recruitmentId;
    private Long accountId;

    public ApplicationId() {}

    @Builder
    public ApplicationId(Long recruitmentId, Long accountId) {
        this.recruitmentId = recruitmentId;
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationId that = (ApplicationId) o;
        return Objects.equals(recruitmentId, that.recruitmentId) && Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recruitmentId, accountId);
    }
}
