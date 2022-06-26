package com.technokratos.dto.request;

import com.technokratos.validation.annotations.PasswordFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@ApiModel(description = "Request to change user's password")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {

    @ApiModelProperty(notes = "Received confirm code", required = true)
    @NotBlank(message = "Confirm code should not be null")
    private String confirmCode;

    @ApiModelProperty(notes = "New password to set on account. Must be valid", required = true, example = "Qwerty007!")
    @PasswordFormat
    @NotBlank(message = "Password should not be null")
    private String newPassword;
}
