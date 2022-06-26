package com.technokratos.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@ApiModel(description = "Information about requested file")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FileInfoResponse {

    @ApiModelProperty(notes = "File identifier")
    private UUID id;

    @ApiModelProperty(hidden = true)
    private UUID imageId;

    @ApiModelProperty(notes = "Mime type", example = "image/jpeg")
    private String mimeType;

    @ApiModelProperty(notes = "Original file name", example = "first.jpg")
    private String originalFileName;

    @ApiModelProperty(notes = "File size", example = "1024512")
    private Long size;
}
