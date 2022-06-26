package com.technokratos.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Response of account state after password recovering via e-mail")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordResponse {

    @ApiModelProperty(notes = "Email address", example = "example@gmail.com")
    private String email;

    @ApiModelProperty(notes = "State of account", example = "CONFIRMED")
    private String state;
}
