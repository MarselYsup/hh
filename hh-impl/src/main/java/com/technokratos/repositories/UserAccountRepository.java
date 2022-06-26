package com.technokratos.repositories;

import com.technokratos.models.RefreshTokenEntity;
import com.technokratos.models.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, UUID> {
    Optional<UserAccountEntity> findByEmail(String email);

    Optional<UserAccountEntity> findByRefreshToken(RefreshTokenEntity refreshToken);

    Optional<UserAccountEntity> findByConfirmCode(String confirmCode);

    boolean existsByEmail(String email);
}
