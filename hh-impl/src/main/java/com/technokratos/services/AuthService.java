package com.technokratos.services;

import com.technokratos.dto.request.*;
import com.technokratos.dto.response.ChangePasswordResponse;
import com.technokratos.dto.response.SignInResponse;
import com.technokratos.dto.response.SignUpResponse;
import org.springframework.http.ResponseEntity;
import com.technokratos.dto.request.RefreshTokenRequest;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface AuthService {
    SignInResponse authenticate(SignInRequest signInRequest);

    SignInResponse updateAccessTokenWithRefreshToken(RefreshTokenRequest tokenRequest);

    SignUpResponse signUp(SignUpEmployeeRequest signUpEmployeeRequest);

    SignUpResponse signUp(SignUpCompanyRequest signUpCompanyRequest);

    SignUpResponse checkConfirmCode(String confirmCode);

    void changePassword(PasswordRecoveryRequest passwordRecoveryRequest);

    ChangePasswordResponse confirmChangePassword(ChangePasswordRequest changePasswordRequest);

    SignInResponse authenticateByOAuth(OAuth2AuthenticationToken authentication);
}
