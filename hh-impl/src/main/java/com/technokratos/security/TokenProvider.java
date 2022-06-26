package com.technokratos.security;

import com.technokratos.models.RefreshTokenEntity;
import com.technokratos.models.UserAccountEntity;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface TokenProvider {
    String generateAccessToken(String email, String role);
    RefreshTokenEntity generateRefreshToken(UserAccountEntity userAccount);
    boolean isValidAccessToken(String token);
    Authentication getAuthenticationFromAccessToken(String token);
    String getAccessTokenFromHeader(HttpServletRequest request);
}
