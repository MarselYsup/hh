package com.technokratos.mappers;

import com.technokratos.dto.request.ResumeRequest;
import com.technokratos.dto.request.VacancyRequest;
import com.technokratos.dto.response.ResumeResponse;
import com.technokratos.dto.response.VacancyResponse;
import com.technokratos.mappers.custom_converters.ActivityConverter;
import com.technokratos.mappers.custom_converters.SkillConverter;
import com.technokratos.models.ResumeEntity;
import com.technokratos.models.VacancyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {PortfolioMapper.class, SkillConverter.class, ActivityConverter.class})
public interface ResumeMapper extends BasicMapper<ResumeEntity, ResumeRequest, ResumeResponse> {

    @Override
    @Mapping(target = "portfolioSet", ignore = true)
    ResumeEntity toEntity(ResumeRequest request);

    List<ResumeEntity> toEntityList(List<ResumeRequest> requestList);

    List<ResumeResponse> toResponseList(List<ResumeEntity> entityList);
}
