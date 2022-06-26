package com.technokratos.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Information of selected activity")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ActivityResponse {

    @ApiModelProperty(notes = "Name of specified activity", example = "programming")
    private String name;

}
