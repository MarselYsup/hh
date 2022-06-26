package com.technokratos.mappers;

import com.technokratos.dto.request.EmployeeInfoRequest;
import com.technokratos.dto.response.EmployeeInfoResponse;
import com.technokratos.models.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = FileInfoMapper.class)
public interface EmployeeMapper extends BasicMapper<EmployeeEntity, EmployeeInfoRequest, EmployeeInfoResponse> {
}
