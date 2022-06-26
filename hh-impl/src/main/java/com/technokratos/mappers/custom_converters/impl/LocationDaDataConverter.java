package com.technokratos.mappers.custom_converters.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technokratos.dto.request.LocationRequest;
import com.technokratos.dto.response.LocationResponse;
import com.technokratos.exceptions.bad_request.LocationBadRequestException;
import com.technokratos.mappers.custom_converters.LocationConverter;
import com.technokratos.models.LocationEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class LocationDaDataConverter implements LocationConverter {

    @Value("${dadata.url}")
    private String daDataUrl;

    @Value("${dadata.secret_key}")
    private String secretToken;

    private final static String LATITUDE = "lat";

    private final static String LONGITUDE = "lon";

    private final static String SUGGESTIONS = "suggestions";

    private final static String VALUE = "value";

    private final static int FIRST_SUGGESTION = 0;

    @Override
    public LocationEntity toEntity(LocationRequest locationRequest) {
        return LocationEntity.builder()
                .latitude(new BigDecimal(locationRequest.getLatitude()))
                .longitude(new BigDecimal(locationRequest.getLongitude()))
                .build();
    }

    @Override
    public LocationResponse toResponse(LocationEntity locationEntity) {
        try {

            Map<String, String> jsonObject = new HashMap<>();

            jsonObject.put(LATITUDE, locationEntity.getLatitude().toString());
            jsonObject.put(LONGITUDE, locationEntity.getLongitude().toString());

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.AUTHORIZATION, secretToken);
            HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonObject, httpHeaders);

            RestTemplate template = new RestTemplate();

            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.readTree(template
                    .postForEntity(daDataUrl, request, String.class)
                    .getBody());
            return LocationResponse.builder()
                    .value(jsonNode.get(SUGGESTIONS)
                            .get(FIRST_SUGGESTION)
                            .get(VALUE)
                            .textValue())
                    .build();
        } catch (JsonProcessingException | NullPointerException e) {
           throw new LocationBadRequestException();
        }
    }


}
