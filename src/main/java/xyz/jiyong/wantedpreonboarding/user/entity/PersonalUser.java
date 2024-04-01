package xyz.jiyong.wantedpreonboarding.user.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("PERSONAL_USER")
public class PersonalUser extends User {

    @Builder
    protected PersonalUser(String email, String password, String name) {
        super(email, password, name);
    }

    protected PersonalUser() {
    }
}
