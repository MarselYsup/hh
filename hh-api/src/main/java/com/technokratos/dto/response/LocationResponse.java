package com.technokratos.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Location information")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LocationResponse {

    @ApiModelProperty(notes = "Received address", example = "Kazan, Kremlevskaya st., 35")
    private String value;

}
