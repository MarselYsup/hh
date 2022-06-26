package com.technokratos.repositories;

import com.technokratos.models.EducationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EducationRepository extends JpaRepository<EducationEntity, UUID> {
}
