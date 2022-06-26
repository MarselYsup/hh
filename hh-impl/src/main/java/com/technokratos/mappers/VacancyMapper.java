package com.technokratos.mappers;

import com.technokratos.dto.request.VacancyRequest;
import com.technokratos.dto.response.VacancyResponse;
import com.technokratos.mappers.custom_converters.ActivityConverter;
import com.technokratos.mappers.custom_converters.LocationConverter;
import com.technokratos.models.VacancyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {LocationConverter.class, ActivityConverter.class})
public interface VacancyMapper extends BasicMapper<VacancyEntity, VacancyRequest, VacancyResponse> {
    List<VacancyEntity> toEntityList(List<VacancyRequest> requestList);
    List<VacancyResponse> toResponseList(List<VacancyEntity> entityList);
}
