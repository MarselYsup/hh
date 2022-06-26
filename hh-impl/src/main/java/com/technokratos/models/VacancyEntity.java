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
@EqualsAndHashCode(of = {"id"})
@Table(name = "vacancy")
public class VacancyEntity extends BaseEntity{
    @Column(nullable = false)
    private String title;

    @Column(name = "min_salary")
    private Integer minSalary;

    @Column(name = "max_salary")
    private Integer maxSalary;

    private String experience;

    @Enumerated(EnumType.STRING)
    @Column(name = "employment_type")
    private Employment employment;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private LocationEntity location;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private ActivityEntity activity;

    @OneToMany(mappedBy = "vacancy", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ViewVacancyEntity> viewVacancySet;
}
