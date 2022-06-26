package com.technokratos.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "skill")
public class SkillEntity extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String skill;

    @ManyToMany(mappedBy = "skillSet")
    private List<ResumeEntity> resumeList;
}
