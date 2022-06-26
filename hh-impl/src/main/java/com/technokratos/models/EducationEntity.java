package com.technokratos.models;

import com.technokratos.enums.EducationLevel;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "education_detail")
public class EducationEntity extends BaseEntity{
    @Column(name = "university_name")
    private String universityName;

    @Column(name = "institute_name")
    private String instituteName;

    private String major;

    @Column(name = "starting_date")
    private Instant startingDate;

    @Column(name = "completion_date")
    private Instant completionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private EducationLevel educationLevel;
}
