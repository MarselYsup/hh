package com.technokratos.mappers;

import com.technokratos.dto.request.FileInfoRequest;
import com.technokratos.dto.response.FileInfoResponse;
import com.technokratos.models.FileInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FileInfoMapper extends BasicMapper<FileInfoEntity, FileInfoRequest, FileInfoResponse> {
}
