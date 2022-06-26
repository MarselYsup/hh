package com.technokratos.dto.request;

import com.technokratos.enums.Employment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Set;

@ApiModel(description = "Information to fill resume")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResumeRequest {

    @ApiModelProperty(notes = "Title of resume", required = true, example = "Junior python developer")
    @NotBlank(message = "Title should not be null")
    @Size(min = 2, max = 255, message = "Title should be between {min} and {max}")
    private String title;

    @ApiModelProperty(notes = "Experience in selected sphere", required = true)
    @NotBlank(message = "Experience should not be null")
    @Size(min = 3, max = 255, message = "Experience should be between {min} and {max}")
    private String experience;

    @ApiModelProperty(notes = "Employment type", required = true, example = "FULL_TIME")
    @NotNull(message = "Employment cannot be null")
    private Employment employment;

    @ApiModelProperty(notes = "Desired salary", required = false, example = "40000")
    @Positive(message = "Salary should be positive")
    private Integer salary;

    @ApiModelProperty(notes = "Additional information", required = false)
    private String information;

    @ApiModelProperty(notes = "Information about education", required = false)
    @Valid
    private EducationRequest education;

    @ApiModelProperty(notes = "Activity name", required = true, example = "programming")
    @Valid
    private ActivityRequest activity;

    @ApiModelProperty(notes = "Set of skills", required = false)
    private Set<@Valid SkillRequest> skillSet;

    @ApiModelProperty(notes = "Set of portfolios with files", required = false)
    private Set<PortfolioRequest> portfolioSet;
}
