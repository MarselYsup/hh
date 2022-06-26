package com.technokratos.dto.request;

import com.technokratos.enums.EducationLevel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.Instant;

@ApiModel(description = "Additional information about education")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EducationRequest {

    @ApiModelProperty(notes = "Name of university", required = true, example = "KFU")
    @NotBlank(message = "University name should not be null")
    @Size(min = 2, max = 255, message = "University name should be between {min} and {max}")
    private String universityName;

    @ApiModelProperty(notes = "Name of institute", required = true, example = "ITIS")
    @NotBlank(message = "Institute name should not be null")
    @Size(min = 2, max = 255, message = "Institute name should be between {min} and {max}")
    private String instituteName;

    @ApiModelProperty(notes = "Major", required = true, example = "Programming engineer")
    @NotBlank(message = "Major should not be null")
    @Size(min = 2, max = 255, message = "Major should be between {min} and {max}")
    private String major;

    @ApiModelProperty(notes = "Start date of training", required = true)
    @Past(message = "Starting date cannot be in future")
    @NotNull(message = "Starting date cannot be null")
    private Instant startingDate;

    @ApiModelProperty(notes = "Estimated date of completion of training", required = false)
    @NotNull(message = "Completion date cannot be null")
    private Instant completionDate;

    @ApiModelProperty(notes = "Level of education", required = true, example = "BACHELOR")
    @NotNull(message = "Education level cannot be null")
    private EducationLevel educationLevel;
}
