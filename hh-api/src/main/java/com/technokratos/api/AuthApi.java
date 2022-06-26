package com.technokratos.api;

import com.technokratos.dto.request.*;
import com.technokratos.dto.response.ChangePasswordResponse;
import com.technokratos.dto.response.SignInResponse;
import com.technokratos.dto.response.SignUpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

@Api(
        value = "Authentication micro-service",
        description = "Endpoints to get tokens or register account or change password"
)
@RequestMapping("/hh/v1/auth")
public interface AuthApi {

    @ApiOperation(
            value = "Sign in endpoint",
            response = SignInResponse.class
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request parameters supplied"),
            @ApiResponse(code = 403, message = "Requested sign in data is incorrect")
    })
    @PostMapping(path = "/sign-in", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    SignInResponse signIn(@RequestBody SignInRequest signInRequest);

    @ApiOperation(
            value = "Refresh token endpoint",
            response = SignInResponse.class
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request parameters supplied"),
            @ApiResponse(code = 403, message = "Requested user are not authorized")
    })
    @PostMapping(path ="/refresh-token", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    SignInResponse refreshToken(@RequestBody RefreshTokenRequest tokenRequest);

    @ApiOperation(
            value = "Sign in via OAuth2 endpoint",
            response = SignInResponse.class
    )
    @GetMapping(path = "/oauth2")
    @ResponseStatus(HttpStatus.OK)
    SignInResponse signInViaOAuth(OAuth2AuthenticationToken auth);

    @ApiOperation(
            value = "Sign up as company endpoint",
            response = SignUpResponse.class,
            code = 201
    )
    @ApiResponses(
            @ApiResponse(code = 400, message = "E-mail already taken")
    )
    @PostMapping(path = "/company/sign-up", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    SignUpResponse signUp(@RequestBody SignUpCompanyRequest signUpCompanyRequest);

    @ApiOperation(
            value = "Sign up as employee endpoint",
            response = SignUpResponse.class,
            code = 201
    )
    @ApiResponses(
            @ApiResponse(code = 400, message = "E-mail already taken")
    )
    @PostMapping(path = "/employee/sign-up", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    SignUpResponse signUp(@RequestBody SignUpEmployeeRequest signUpEmployeeRequest);

    @ApiOperation(
            value = "Sign up confirm endpoint",
            response = SignUpResponse.class
    )
    @ApiResponses(
            @ApiResponse(code = 400, message = "Invalid request parameters supplied")
    )
    @GetMapping(path = "/{confirmCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    SignUpResponse confirmSignUp(@PathVariable String confirmCode);

    @ApiOperation(
            value = "Change password endpoint"
    )
    @ApiResponses(
            @ApiResponse(code = 400, message = "Invalid request parameters supplied")
    )
    @PostMapping(path = "/change-password", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    void changePassword(@RequestBody PasswordRecoveryRequest passwordRecoveryRequest);

    @ApiOperation(
            value = "Confirm changing password endpoint",
            response = ChangePasswordResponse.class
    )
    @ApiResponses(
            @ApiResponse(code = 400, message = "Invalid request parameters supplied")
    )
    @PostMapping(path = "/confirm-password", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ChangePasswordResponse confirmChangePassword(@RequestBody ChangePasswordRequest changePasswordRequest);
}
