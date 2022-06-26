package com.technokratos.services.impl;

import com.technokratos.dto.response.SignInResponse;
import com.technokratos.exceptions.token.ExpiredTokenException;
import com.technokratos.models.RefreshTokenEntity;
import com.technokratos.models.UserAccountEntity;
import com.technokratos.repositories.UserAccountRepository;
import com.technokratos.security.TokenProvider;
import com.technokratos.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenProvider tokenProvider;

    private final UserAccountRepository userAccountRepository;

    @Override
    public SignInResponse createTokens(UserAccountEntity userAccount) {
        String accessToken = tokenProvider.generateAccessToken(userAccount.getEmail()
                ,"ROLE_" + userAccount.getRole().toString());

        RefreshTokenEntity refreshToken = tokenProvider.generateRefreshToken(userAccount);
        userAccount = saveRefreshTokenInUserAccount(userAccount, refreshToken);
        return SignInResponse.builder()
                .refreshToken(userAccount.getRefreshToken().getId().toString())
                .accessToken(accessToken)
                .email(userAccount.getEmail())
                .role(userAccount.getRole().toString())
                .build();
    }

    @Override
    public UserAccountEntity saveRefreshTokenInUserAccount(UserAccountEntity userAccount, RefreshTokenEntity refreshToken) {
        userAccount.setRefreshToken(refreshToken);
        return userAccountRepository.save(userAccount);
    }

    @Override
    public boolean checkExpiredTimeRefreshToken(RefreshTokenEntity refreshToken) {
        if(refreshToken == null) return false;

        if(!refreshToken.getExpiredTime().isAfter(Instant.now())) {
            throw new ExpiredTokenException();
        }
        return true;
    }
}
