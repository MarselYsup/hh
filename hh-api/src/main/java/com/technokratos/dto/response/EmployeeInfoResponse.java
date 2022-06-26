package com.technokratos.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@ApiModel(description = "Information about requested employee")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeInfoResponse {

    @ApiModelProperty(notes = "Identifier of employee")
    private UUID id;

    @ApiModelProperty(notes = "First name", example = "Ivan")
    private String firstName;

    @ApiModelProperty(notes = "Last name", example = "Ivanov")
    private String lastName;

    @ApiModelProperty(notes = "Date of birth")
    private Instant dateOfBirth;

    @ApiModelProperty(notes = "Phone number", example = "+79001234567")
    private String phoneNumber;

    @ApiModelProperty(notes = "Gender", example = "male")
    private String gender;

    @ApiModelProperty(notes = "City", example = "Moscow")
    private String city;

    @ApiModelProperty(notes = "Employee avatar")
    private FileInfoResponse employeeAvatar;
}
