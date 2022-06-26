package com.technokratos.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@ApiModel(description = "Request to recover password via e-mail")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRecoveryRequest {

    @ApiModelProperty(notes = "Email address", required = true, example = "example@gmail.com")
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email should not be null")
    private String email;
}
