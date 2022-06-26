package com.technokratos.controllers;

import com.technokratos.api.VacancyApi;
import com.technokratos.dto.request.VacancyRequest;
import com.technokratos.dto.response.VacancyListResponse;
import com.technokratos.dto.response.VacancyResponse;
import com.technokratos.enums.VacancySort;
import com.technokratos.models.VacancyEntity;
import com.technokratos.services.VacancyService;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class VacancyController implements VacancyApi<UserDetails, VacancyEntity> {
    private final VacancyService vacancyService;

    @Override
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    public VacancyResponse createVacancy(@Valid VacancyRequest vacancyRequest, UserDetails userDetails ) {
        return vacancyService.createVacancy(vacancyRequest, userDetails);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    public VacancyResponse updateVacancy(@Valid VacancyRequest vacancyRequest, UUID id, UserDetails userDetails) {
        return vacancyService.updateVacancy(vacancyRequest, id, userDetails);
    }

    @Override
    public VacancyResponse getVacancy(UUID id, UserDetails userDetails) {
        return vacancyService.getVacancy(id, userDetails);
    }

    @Override
    public VacancyListResponse getVacancyList(Integer page, Integer size, VacancySort vacancySort,
                                              @Join(path = "activity", alias = "act")
                                              @And({
                                                      @Spec(path = "title", spec = Like.class ),
                                                      @Spec(path = "employment", spec = Equal.class),
                                                      @Spec(path = "minSalary", spec = GreaterThanOrEqual.class),
                                                      @Spec(path = "maxSalary", spec = LessThanOrEqual.class),
                                                      @Spec(path = "act.activity", params = "activity",
                                                              spec = Equal.class)
                                              })
                                              Specification<VacancyEntity> vacancySpec) {
        return vacancyService.getVacancyList(page, size, vacancySort, vacancySpec);
    }

}
