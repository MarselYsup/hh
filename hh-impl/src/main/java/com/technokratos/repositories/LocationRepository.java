package com.technokratos.repositories;

import com.technokratos.models.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocationRepository extends JpaRepository<LocationEntity, UUID> {
}
