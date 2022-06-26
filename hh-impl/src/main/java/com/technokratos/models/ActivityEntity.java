package com.technokratos.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "area_of_activity")
public class ActivityEntity extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String activity;

    @OneToMany(mappedBy = "activity")
    private List<VacancyEntity> vacancyList;

    @OneToMany(mappedBy = "activity")
    private List<ResumeEntity> resumeList;
}
