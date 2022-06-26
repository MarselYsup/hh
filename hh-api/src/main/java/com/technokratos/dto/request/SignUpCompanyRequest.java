package com.technokratos.dto.request;

import com.technokratos.validation.annotations.PasswordFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@ApiModel(description = "Sign up as company")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpCompanyRequest {

    @ApiModelProperty(notes = "E-mail", required = true, example = "example@gmail.com")
    @Email(message = "Email address is not valid")
    @NotBlank(message = "Email should not be null")
    private String email;

    @ApiModelProperty(notes = "Password", required = true, example = "Qwerty007!")
    @PasswordFormat
    @NotBlank(message = "Password should not be null")
    private String password;
}
