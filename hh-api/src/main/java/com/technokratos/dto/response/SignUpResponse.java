package com.technokratos.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Result of sign up - role and status")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponse {

    @ApiModelProperty(notes = "User's E-mail", example = "example@gmail.com")
    private String email;

    @ApiModelProperty(notes = "User's role", example = "ROLE_COMPANY")
    private String role;

    @ApiModelProperty(notes = "Current status", example = "NOT_CONFIRMED")
    private String status;
}
