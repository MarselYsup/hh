package com.technokratos.models;

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
@Table(name = "company")
public class CompanyEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(name = "profile_description", columnDefinition = "text")
    private String profileDescription;

    @Column(name = "website_url", columnDefinition = "varchar(500)")
    private String websiteUrl;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserAccountEntity userAccount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id")
    private FileInfoEntity companyAvatar;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<VacancyEntity> vacancySet;
}
