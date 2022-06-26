package com.technokratos.dto.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@ApiModel(description = "Response with company info")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyInfoResponse {

    @ApiModelProperty(notes = "Identifier of company")
    private UUID id;

    @ApiModelProperty(notes = "Identifier of company avatar")
    private FileInfoResponse companyAvatar;

    @ApiModelProperty(notes = "Company names", example = "iSpring")
    private String name;

    @ApiModelProperty(notes = "Extended description of company", example = "Best IT company in city")
    private String profileDescription;

    @ApiModelProperty(notes = "Website of company", example = "https://example.com")
    private String websiteUrl;
}
