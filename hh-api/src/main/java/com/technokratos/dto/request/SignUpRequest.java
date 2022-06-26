package com.technokratos.dto.request;

import com.technokratos.validation.annotations.PasswordFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@ApiModel(description = "Request to sign up")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @ApiModelProperty(notes = "E-mail", required = true, example = "example@gmail.com")
    @Email(message = "Email address is not valid")
    private String email;

    @ApiModelProperty(notes = "Password", required = true, example = "Qwerty007!")
    @PasswordFormat
    private String password;
}
