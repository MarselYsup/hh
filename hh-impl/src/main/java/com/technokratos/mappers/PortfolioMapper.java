package com.technokratos.mappers;

import com.technokratos.dto.request.PortfolioRequest;
import com.technokratos.dto.response.PortfolioResponse;
import com.technokratos.models.PortfolioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = FileInfoMapper.class)
public interface PortfolioMapper extends BasicMapper<PortfolioEntity, PortfolioRequest, PortfolioResponse> {
}
