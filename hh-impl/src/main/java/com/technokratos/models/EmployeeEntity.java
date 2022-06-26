package com.technokratos.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@EqualsAndHashCode(of = {"id"})
@Table(name = "employee")
public class EmployeeEntity extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserAccountEntity userAccount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id")
    private FileInfoEntity employeeAvatar;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth")
    private Instant dateOfBirth;

    @Column(name = "phone_number", columnDefinition = "varchar(20)", nullable = false)
    private String phoneNumber;

    @Column(columnDefinition = "varchar(20)")
    private String gender;

    private String city;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ResumeEntity> resumeSet;
}
