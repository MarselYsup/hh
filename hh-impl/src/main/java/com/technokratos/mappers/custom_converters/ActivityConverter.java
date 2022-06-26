package com.technokratos.mappers.custom_converters;

import com.technokratos.dto.request.ActivityRequest;
import com.technokratos.dto.response.ActivityResponse;
import com.technokratos.mappers.BasicMapper;
import com.technokratos.models.ActivityEntity;

public interface ActivityConverter extends BasicMapper<ActivityEntity, ActivityRequest, ActivityResponse> {
}
