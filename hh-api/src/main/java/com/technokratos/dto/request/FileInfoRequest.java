package com.technokratos.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@ApiModel(description = "Request to get file by id")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FileInfoRequest {
    @ApiModelProperty(notes = "Id of file", required = true)
    private UUID id;
}
