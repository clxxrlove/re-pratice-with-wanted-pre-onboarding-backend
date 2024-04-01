package xyz.jiyong.wantedpreonboarding.user.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("ENTERPRISE_USER")
public class EnterpriseUser extends User {

    private String country;
    private String region;

    @Builder
    protected EnterpriseUser(String email, String password, String name, String country, String region) {
        super(email, password, name);
        this.country = country;
        this.region = region;
    }

    protected EnterpriseUser() {
    }
}
