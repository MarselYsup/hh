package com.technokratos.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"vacancy", "userAccount"})
@Setter
@SuperBuilder
@Entity
@Table(name = "view_vacancy")
public class ViewVacancyEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private VacancyEntity vacancy;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccountEntity userAccount;
}
