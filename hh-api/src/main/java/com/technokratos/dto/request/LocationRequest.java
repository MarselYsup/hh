package com.technokratos.dto.request;


import com.technokratos.validation.annotations.LatitudeFormat;
import com.technokratos.validation.annotations.LongitudeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@ApiModel(description = "Specifying the location of office")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LocationRequest {

    @ApiModelProperty(notes = "Latitude", required = true, example = "55.792139")
    @LatitudeFormat
    @NotBlank(message = "Latitude should not be null")
    private String latitude;

    @ApiModelProperty(notes = "Longitude", required = true, example = "49.122135")
    @LongitudeFormat
    @NotBlank(message = "Longitude should not be null")
    private String longitude;
}
