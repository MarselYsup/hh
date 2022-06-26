package com.technokratos.repositories;

import com.technokratos.models.CompanyEntity;
import com.technokratos.models.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository  extends JpaRepository<CompanyEntity, UUID> {
    Optional<CompanyEntity> findByUserAccount(UserAccountEntity userAccount);
}
