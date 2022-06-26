package com.technokratos.mappers;

import com.technokratos.dto.request.CompanyInfoRequest;
import com.technokratos.dto.response.CompanyInfoResponse;
import com.technokratos.models.CompanyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = FileInfoMapper.class)
public interface CompanyMapper extends BasicMapper<CompanyEntity, CompanyInfoRequest, CompanyInfoResponse> {
}
