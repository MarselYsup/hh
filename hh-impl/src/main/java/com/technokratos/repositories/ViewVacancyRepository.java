package com.technokratos.repositories;

import com.technokratos.models.ViewVacancyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ViewVacancyRepository extends JpaRepository<ViewVacancyEntity, UUID> {
}
