package com.technokratos.mappers.custom_converters.impl;

import com.technokratos.dto.request.SkillRequest;
import com.technokratos.dto.response.SkillResponse;
import com.technokratos.exceptions.not_found.SkillNotFoundException;
import com.technokratos.mappers.custom_converters.SkillConverter;
import com.technokratos.models.SkillEntity;
import com.technokratos.repositories.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SkillConverterImpl implements SkillConverter {

    private final SkillRepository skillRepository;

    @Override
    public SkillEntity toEntity(SkillRequest skillRequest) {
        return skillRepository.findBySkill(skillRequest.getSkill())
                .orElseThrow(SkillNotFoundException::new);
    }

    @Override
    public SkillResponse toResponse(SkillEntity skillEntity) {
        return SkillResponse.builder()
                .skill(skillEntity.getSkill())
                .build();
    }
}
