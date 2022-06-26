package com.technokratos.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Portfolio information")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PortfolioResponse {

    @ApiModelProperty(notes = "File info")
    private FileInfoResponse fileInfo;
}
