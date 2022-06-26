package com.technokratos.models;

import com.technokratos.enums.Employment;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "resume")
public class ResumeEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "education_id")
    private EducationEntity education;

    @Column(nullable = false)
    private String title;

    private String experience;

    @Enumerated(EnumType.STRING)
    @Column(name = "employment_type")
    private Employment employment;

    private Integer salary;

    @Column(name = "information", columnDefinition = "text")
    private String information;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private ActivityEntity activity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "resume_skill", joinColumns = @JoinColumn(name = "resume_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id")
    )
    private Set<SkillEntity> skillSet;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<PortfolioEntity> portfolioSet;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<ViewResumeEntity> viewResumeSet;
}
