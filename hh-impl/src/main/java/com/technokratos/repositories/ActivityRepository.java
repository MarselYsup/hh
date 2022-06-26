package com.technokratos.repositories;

import com.technokratos.models.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ActivityRepository extends JpaRepository<ActivityEntity, UUID> {

    Optional<ActivityEntity> findByActivity(String name);

}
