package com.technokratos.repositories;

import com.technokratos.models.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ResumeRepository extends JpaRepository<ResumeEntity, UUID>, JpaSpecificationExecutor<ResumeEntity> {
}
