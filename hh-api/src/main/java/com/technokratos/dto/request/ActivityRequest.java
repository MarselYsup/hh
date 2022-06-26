package com.technokratos.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@ApiModel(description = "Request of selected activity")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ActivityRequest {

    @ApiModelProperty(notes = "Name of specified activity", required = true, example = "programing")
    @Size(min = 1, max = 255, message = "Activity should be between {min} and {max}")
    private String name;
}
