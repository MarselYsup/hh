package com.technokratos.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Result of sign in - tokens and role")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse {

    @ApiModelProperty(notes = "Access token")
    private String accessToken;

    @ApiModelProperty(notes = "Refresh token")
    private String refreshToken;

    @ApiModelProperty(notes = "User's E-mail", example = "example@gmail.com")
    private String email;

    @ApiModelProperty(notes = "User's role", example = "ROLE_EMPLOYEE")
    private String role;
}
