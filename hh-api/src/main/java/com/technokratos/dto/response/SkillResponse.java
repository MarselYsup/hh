package com.technokratos.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Skill info")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SkillResponse {

    @ApiModelProperty(notes = "Skill name", example = "teamwork")
    private String skill;
}
