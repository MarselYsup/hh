package com.technokratos.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@Entity
@Table(name = "portfolio")
public class PortfolioEntity extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "file_id")
    private FileInfoEntity fileInfo;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private ResumeEntity resume;
}
