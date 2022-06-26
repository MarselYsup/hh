package com.technokratos.mappers.custom_converters;

import com.technokratos.dto.request.LocationRequest;
import com.technokratos.dto.response.LocationResponse;
import com.technokratos.mappers.BasicMapper;
import com.technokratos.models.LocationEntity;

public interface LocationConverter extends BasicMapper<LocationEntity, LocationRequest, LocationResponse> {
}
