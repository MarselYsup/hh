package com.technokratos.dto.response;

import com.technokratos.enums.Employment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@ApiModel(description = "Information about requested vacancy")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VacancyResponse {

    @ApiModelProperty(notes = "Identifier of vacancy")
    private UUID id;

    @ApiModelProperty(notes = "Vacancy title", example = "Junior python developer")
    private String title;

    @ApiModelProperty(notes = "Minimum salary", example = "30000")
    private Integer minSalary;

    @ApiModelProperty(notes = "Maximum salary", example = "100000")
    private Integer maxSalary;

    @ApiModelProperty(notes = "Necessary experience", example = "At least one year in commerce developing")
    private String experience;

    @ApiModelProperty(notes = "Employment type", example = "FULL_TIME")
    private Employment employment;

    @ApiModelProperty(notes = "Address of office")
    private LocationResponse location;

    @ApiModelProperty(notes = "Activity")
    private ActivityResponse activity;

}
