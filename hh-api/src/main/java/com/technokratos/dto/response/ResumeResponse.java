package com.technokratos.dto.response;

import com.technokratos.dto.request.EducationRequest;
import com.technokratos.dto.request.PortfolioRequest;
import com.technokratos.dto.request.SkillRequest;
import com.technokratos.enums.Employment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@ApiModel(description = "Information about selected resume")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResumeResponse {

    @ApiModelProperty(notes = "Identifier of resume")
    private UUID id;

    @ApiModelProperty(notes = "Resume title", example = "Junior python developer")
    private String title;

    @ApiModelProperty(notes = "Experience", example = "Two years in commerce developing")
    private String experience;

    @ApiModelProperty(notes = "Employment type", example = "FULL_TIME")
    private Employment employment;

    @ApiModelProperty(notes = "Salary", example = "50000")
    private Integer salary;

    @ApiModelProperty(notes = "Additional information", example = "love camping")
    private String information;

    @ApiModelProperty(notes = "Information about education")
    private EducationResponse education;

    @ApiModelProperty(notes = "Activity name")
    private ActivityResponse activity;

    @ApiModelProperty(notes = "Skill set")
    private Set<SkillResponse> skillSet;

    @ApiModelProperty(notes = "Portfolio set")
    private Set<PortfolioResponse> portfolioSet;
}
