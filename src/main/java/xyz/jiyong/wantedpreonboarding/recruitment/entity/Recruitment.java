package xyz.jiyong.wantedpreonboarding.recruitment.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import xyz.jiyong.wantedpreonboarding.user.entity.EnterpriseUser;

import java.util.List;

@Entity
@Getter
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private EnterpriseUser enterpriseId;
    private String position;
    private Long guarantee;
    private String content;

    @Column(name = "tech_stack")
    private String techStack;

    @OneToMany(mappedBy = "recruitmentId")
    private List<Application> applications;

    @Builder
    protected Recruitment(EnterpriseUser enterpriseId, String position, Long guarantee, String content, String techStack) {
        this.enterpriseId = enterpriseId;
        this.position = position;
        this.guarantee = guarantee;
        this.content = content;
        this.techStack = techStack;
    }

    protected Recruitment() {
    }
}
