package com.technokratos.dto.request;


import com.technokratos.validation.annotations.PhoneFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.Instant;

@ApiModel(description = "Information about employee")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeInfoRequest {

    @ApiModelProperty(notes = "First name", required = true, example = "Ivan")
    @Size(min = 2, max = 255, message = "First name should be between {min} and {max}")
    @NotBlank(message = "First name should not be null")
    private String firstName;

    @ApiModelProperty(notes = "Last name", required = true, example = "Ivanov")
    @Size(min = 2, max = 255, message = "Last name should be between {min} and {max}")
    @NotBlank(message = "Last name should not be null")
    private String lastName;

    @ApiModelProperty(notes = "Date of birth", required = false)
    @Past(message = "Date of birth cannot be in future!")
    private Instant dateOfBirth;

    @ApiModelProperty(notes = "Phone number", required = true, example = "+79001234567")
    @PhoneFormat
    @NotBlank(message = "Phone number should not be null")
    private String phoneNumber;

    @ApiModelProperty(notes = "Gender", required = false)
    private String gender;

    @ApiModelProperty(notes = "City", required = false)
    private String city;
}
