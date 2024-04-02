package xyz.jiyong.wantedpreonboarding.user.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import xyz.jiyong.wantedpreonboarding.recruitment.entity.Recruitment;

import java.util.List;

@Entity
@Getter
@DiscriminatorValue("ENTERPRISE_USER")
public class EnterpriseUser extends User {

    private String country;
    private String region;

    @OneToMany(mappedBy = "enterpriseId")
    private List<Recruitment> recruitments;

    @Builder
    protected EnterpriseUser(String email, String password, String name, String country, String region) {
        super(email, password, name);
        this.country = country;
        this.region = region;
    }

    protected EnterpriseUser() {
    }
}
