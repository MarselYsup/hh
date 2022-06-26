package com.technokratos.controllers;

import com.technokratos.api.AuthApi;
import com.technokratos.dto.request.*;
import com.technokratos.dto.response.ChangePasswordResponse;
import com.technokratos.dto.response.SignInResponse;
import com.technokratos.dto.response.SignUpResponse;
import com.technokratos.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class AuthController implements AuthApi {
    private final AuthService authService;

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) {
        return authService.authenticate(signInRequest);
    }

    @Override
    public SignInResponse refreshToken(RefreshTokenRequest tokenRequest) {
        return authService.updateAccessTokenWithRefreshToken(tokenRequest);
    }

    @Override
    public SignUpResponse signUp(@Valid SignUpEmployeeRequest signUpEmployeeRequest) {
        return authService.signUp(signUpEmployeeRequest);
    }

    @Override
    public SignUpResponse signUp(@Valid SignUpCompanyRequest signUpCompanyRequest) {
        return authService.signUp(signUpCompanyRequest);
    }

    @Override
    public SignUpResponse confirmSignUp(String confirmCode) {
        return authService.checkConfirmCode(confirmCode);
    }

    @Override
    public void changePassword(@Valid PasswordRecoveryRequest passwordRecoveryRequest) {
        authService.changePassword(passwordRecoveryRequest);
    }

    @Override
    public ChangePasswordResponse confirmChangePassword(@Valid ChangePasswordRequest changePasswordRequest) {
        return authService.confirmChangePassword(changePasswordRequest);
    }

    @Override
    public SignInResponse signInViaOAuth(OAuth2AuthenticationToken auth) {
        return authService.authenticateByOAuth(auth);
    }
}
