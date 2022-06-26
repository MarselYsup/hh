package com.technokratos.repositories;

import com.technokratos.models.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PortfolioRepository extends JpaRepository<PortfolioEntity, UUID> {
}
