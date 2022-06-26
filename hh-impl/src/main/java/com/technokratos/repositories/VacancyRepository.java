package com.technokratos.repositories;

import com.technokratos.enums.Employment;
import com.technokratos.models.ActivityEntity;
import com.technokratos.models.VacancyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface VacancyRepository extends JpaRepository<VacancyEntity, UUID>, JpaSpecificationExecutor<VacancyEntity> {

}
