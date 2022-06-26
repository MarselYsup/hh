package com.technokratos.mappers;

import com.technokratos.dto.request.EducationRequest;
import com.technokratos.dto.response.EducationResponse;
import com.technokratos.models.EducationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EducationMapper extends BasicMapper<EducationEntity, EducationRequest, EducationResponse> {
}
