package com.technokratos.services;

import com.technokratos.dto.response.SignInResponse;
import com.technokratos.models.RefreshTokenEntity;
import com.technokratos.models.UserAccountEntity;

public interface TokenService {
    SignInResponse createTokens(UserAccountEntity userAccount);

    UserAccountEntity saveRefreshTokenInUserAccount(UserAccountEntity userAccount, RefreshTokenEntity refreshToken);

    boolean checkExpiredTimeRefreshToken(RefreshTokenEntity refreshToken);
}
