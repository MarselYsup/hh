package com.technokratos.mappers.custom_converters.impl;

import com.technokratos.dto.request.ActivityRequest;
import com.technokratos.dto.response.ActivityResponse;
import com.technokratos.exceptions.not_found.ActivityNotFoundException;
import com.technokratos.mappers.BasicMapper;
import com.technokratos.mappers.custom_converters.ActivityConverter;
import com.technokratos.models.ActivityEntity;
import com.technokratos.repositories.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActivityConverterImpl implements ActivityConverter {

    private final ActivityRepository activityRepository;

    @Override
    public ActivityEntity toEntity(ActivityRequest activityRequest) {
        return activityRepository.findByActivity(activityRequest.getName())
                .orElseThrow(ActivityNotFoundException::new);

    }

    @Override
    public ActivityResponse toResponse(ActivityEntity activityEntity) {
        return ActivityResponse.builder()
                .name(activityEntity.getActivity())
                .build();
    }
}
