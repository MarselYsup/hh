package com.technokratos.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@ApiModel(description = "Request to exchange refresh token to new pair of access and refresh token")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequest {

    @ApiModelProperty(notes = "Refresh token", required = true)
    private UUID refreshToken;
}
