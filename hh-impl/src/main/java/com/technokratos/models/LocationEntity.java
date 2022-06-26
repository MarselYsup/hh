package com.technokratos.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "location")
public class LocationEntity extends BaseEntity{
    @Column(nullable = false, scale = 2, precision = 10)
    private BigDecimal latitude;

    @Column(nullable = false, scale = 2, precision = 10)
    private BigDecimal longitude;

    @OneToOne(mappedBy = "location")
    private VacancyEntity vacancy;
}
