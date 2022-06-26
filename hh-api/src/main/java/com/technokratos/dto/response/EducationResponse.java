package com.technokratos.dto.response;

import com.technokratos.enums.EducationLevel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@ApiModel(description = "Information about selected education")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EducationResponse {

    @ApiModelProperty(notes = "Identifier of education")
    private UUID id;

    @ApiModelProperty(notes = "University name", example = "KFU")
    private String universityName;

    @ApiModelProperty(notes = "Institute name", example = "ITIS")
    private String instituteName;

    @ApiModelProperty(notes = "Major name", example = "Programming engineer")
    private String major;

    @ApiModelProperty(notes = "Start date of training")
    private Instant startingDate;

    @ApiModelProperty(notes = "Estimated date of completion of training")
    private Instant completionDate;

    @ApiModelProperty(notes = "Level of education", example = "BACHELOR")
    private EducationLevel educationLevel;
}
