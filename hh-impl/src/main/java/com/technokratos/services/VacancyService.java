package com.technokratos.services;

import com.technokratos.dto.request.VacancyRequest;
import com.technokratos.dto.response.VacancyListResponse;
import com.technokratos.dto.response.VacancyResponse;
import com.technokratos.enums.Employment;
import com.technokratos.enums.VacancySort;
import com.technokratos.models.VacancyEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface VacancyService {

    VacancyResponse createVacancy(VacancyRequest vacancyRequest, UserDetails userDetails);

    VacancyResponse getVacancy(UUID id, UserDetails userDetails);

    VacancyResponse updateVacancy(VacancyRequest vacancyRequest, UUID id, UserDetails userDetails);

    VacancyListResponse getVacancyList(Integer page, Integer size, VacancySort vacancySort, Specification<VacancyEntity> specification);
}
