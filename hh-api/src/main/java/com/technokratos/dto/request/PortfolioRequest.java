package com.technokratos.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Request to get file in portfolio")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PortfolioRequest {

    @ApiModelProperty(notes = "Information about file, it's id", required = true)
    private FileInfoRequest fileInfo;
}
