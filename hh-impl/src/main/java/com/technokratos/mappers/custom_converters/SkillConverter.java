package com.technokratos.mappers.custom_converters;

import com.technokratos.dto.request.SkillRequest;
import com.technokratos.dto.response.SkillResponse;
import com.technokratos.mappers.BasicMapper;
import com.technokratos.models.SkillEntity;

public interface SkillConverter extends BasicMapper<SkillEntity, SkillRequest, SkillResponse> {
}
