package com.technokratos.repositories;

import com.technokratos.models.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SkillRepository extends JpaRepository<SkillEntity, UUID> {
    Optional<SkillEntity> findBySkill(String skill);
}
