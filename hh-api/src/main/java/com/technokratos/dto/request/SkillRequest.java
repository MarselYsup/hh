package com.technokratos.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@ApiModel(description = "Select skill to add to resume")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SkillRequest {

    @ApiModelProperty(notes = "Skill name", required = true, example = "Teamwork")
    @Size(min = 1, max = 255, message = "Skill should be between {min} and {max}")
    private String skill;
}
