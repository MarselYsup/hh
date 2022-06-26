package com.technokratos.models;

import com.technokratos.enums.Role;
import com.technokratos.enums.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@SuperBuilder
@Entity
@Table(name = "user_account")
public class UserAccountEntity extends BaseEntity{

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "confirm_code", unique = true)
    private String confirmCode;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "token_id")
    private RefreshTokenEntity refreshToken;

    @OneToOne(mappedBy = "userAccount", cascade = CascadeType.PERSIST)
    private CompanyEntity company;

    @OneToOne(mappedBy = "userAccount", cascade = CascadeType.PERSIST)
    private EmployeeEntity employee;

    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<ViewResumeEntity> viewResumeList;

    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<ViewVacancyEntity> viewVacancyList;
}
