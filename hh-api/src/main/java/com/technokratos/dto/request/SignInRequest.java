package com.technokratos.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Request to sign in and get pair of access and refresh token")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {
    @ApiModelProperty(notes = "E-mail", required = true, example = "example@gmail.com")
    private String email;

    @ApiModelProperty(notes = "Password", required = true, example = "Qwerty007!")
    private String password;
}
