package xyz.jiyong.wantedpreonboarding.user.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import xyz.jiyong.wantedpreonboarding.recruitment.entity.Application;

import java.util.List;

@Entity
@Getter
@DiscriminatorValue("PERSONAL_USER")
public class PersonalUser extends User {

    @OneToMany(mappedBy = "account_id")
    private List<Application> applications;

    @Builder
    protected PersonalUser(String email, String password, String name) {
        super(email, password, name);
    }

    protected PersonalUser() {
    }
}
