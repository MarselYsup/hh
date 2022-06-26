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
@Setter
@SuperBuilder
@Entity
@Table(name = "view_resume")
public class ViewResumeEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private ResumeEntity resume;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccountEntity userAccount;
}
