package com.technokratos.mappers;

import com.technokratos.dto.request.SignUpEmployeeRequest;
import com.technokratos.dto.request.SignUpRequest;
import com.technokratos.dto.response.SignUpResponse;
import com.technokratos.models.UserAccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SignUpMapper extends BasicMapper<UserAccountEntity, SignUpRequest, SignUpResponse> {
}
