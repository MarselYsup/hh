package com.technokratos.dto.request;

import com.technokratos.enums.Employment;
import com.technokratos.validation.annotations.MinMaxFieldsFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@ApiModel(description = "Information to fill vacancy")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@MinMaxFieldsFormat(minField = "minSalary", maxField = "maxSalary")
public class VacancyRequest {

    @ApiModelProperty(notes = "Title of vacancy", required = true, example = "Junior python developer")
    @NotBlank(message = "Title should not be null")
    @Size(min = 1, max = 255, message = "Title should be between {min} and {max}")
    private String title;

    @ApiModelProperty(notes = "Minimum salary", required = false, example = "20000")
    @Positive(message = "Salary should be positive")
    private Integer minSalary;

    @ApiModelProperty(notes = "Maximum salary", required = false, example = "200000")
    @Positive(message = "Salary should be positive")
    private Integer maxSalary;

    @ApiModelProperty(notes = "Necessary experience in selected sphere", required = false)
    @Size(min = 1, max = 255, message = "Experience should be between {min} and {max}")
    private String experience;

    @ApiModelProperty(notes = "Employment type", required = true, example = "FULL_TIME")
    private Employment employment;

    @ApiModelProperty(notes = "Location of office", required = false)
    @Valid
    private LocationRequest location;

    @ApiModelProperty(notes = "Activity name", required = true, example = "programming")
    @Valid
    private ActivityRequest activity;

}
