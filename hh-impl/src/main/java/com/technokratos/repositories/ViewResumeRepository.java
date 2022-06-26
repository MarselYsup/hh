package com.technokratos.repositories;

import com.technokratos.models.ViewResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ViewResumeRepository extends JpaRepository<ViewResumeEntity, UUID> {
}
