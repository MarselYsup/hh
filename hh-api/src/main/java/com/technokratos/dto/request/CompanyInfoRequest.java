package com.technokratos.dto.request;

import com.technokratos.validation.annotations.WebsiteUrlFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel(description = "Request of company info")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyInfoRequest {

    @ApiModelProperty(notes = "Name of company", required = true, example = "iSpring")
    @Size(min = 1, max = 255, message = "Name should be between {min} and {max}")
    @NotBlank(message = "Name should not be null")
    private String name;

    @ApiModelProperty(notes = "Description of company", required = false)
    private String profileDescription;

    @ApiModelProperty(notes = "Company's website url. Must be valid", required = true, example = "https://example.com")
    @Size(min = 5, max = 500, message = "Website url should be between {min} and {max}")
    @WebsiteUrlFormat
    private String websiteUrl;
}
