package com.technokratos.repositories;

import com.technokratos.models.EmployeeEntity;
import com.technokratos.models.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {
    Optional<EmployeeEntity> findByUserAccount(UserAccountEntity userAccount);
}
