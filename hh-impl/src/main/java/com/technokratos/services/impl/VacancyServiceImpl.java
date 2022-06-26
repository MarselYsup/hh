package com.technokratos.services.impl;

import com.technokratos.dto.request.VacancyRequest;
import com.technokratos.dto.response.VacancyListResponse;
import com.technokratos.dto.response.VacancyResponse;
import com.technokratos.enums.Role;
import com.technokratos.enums.VacancySort;
import com.technokratos.exceptions.not_found.VacancyNotFoundException;
import com.technokratos.exceptions.restricted_permission.ChangeVacancyRestrictedPermissionException;
import com.technokratos.mappers.VacancyMapper;
import com.technokratos.models.CompanyEntity;
import com.technokratos.models.UserAccountEntity;
import com.technokratos.models.VacancyEntity;
import com.technokratos.models.ViewVacancyEntity;
import com.technokratos.repositories.VacancyRepository;
import com.technokratos.services.CompanyService;
import com.technokratos.services.UserAccountService;
import com.technokratos.services.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

import static com.technokratos.util.consts.PageConst.DEFAULT_PAGE;
import static com.technokratos.util.consts.PageConst.DEFAULT_SIZE;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;

    private final CompanyService companyService;

    private final UserAccountService userAccountService;

    private final VacancyMapper vacancyMapper;

    @Override
    @Transactional
    public VacancyResponse createVacancy(VacancyRequest vacancyRequest, UserDetails userDetails) {
        VacancyEntity vacancyEntity = vacancyMapper.toEntity(vacancyRequest);

        CompanyEntity companyEntity = companyService.getCompanyByUserDetails(userDetails);
        vacancyEntity.setCompany(companyEntity);

        return vacancyMapper.toResponse(vacancyRepository.save(vacancyEntity));

    }

    @Override
    public VacancyResponse getVacancy(UUID id, UserDetails userDetails) {
        VacancyEntity vacancyEntity = vacancyRepository.findById(id).orElseThrow(VacancyNotFoundException::new);

        UserAccountEntity userAccountEntity = userAccountService.getUserAccountByEmail(userDetails.getUsername());

        if(userAccountEntity.getRole().equals(Role.EMPLOYEE)) {

            ViewVacancyEntity viewVacancyEntity = ViewVacancyEntity.builder()
                    .vacancy(vacancyEntity)
                    .userAccount(userAccountEntity)
                    .build();

            Set<ViewVacancyEntity> viewVacancyEntitySet = Optional.ofNullable(vacancyEntity.getViewVacancySet())
                    .orElseGet(HashSet::new);

            ViewVacancyEntity finalViewVacancyEntity = viewVacancyEntitySet
                    .stream()
                    .filter(e -> e.equals(viewVacancyEntity))
                    .findFirst()
                    .orElse(viewVacancyEntity);
            finalViewVacancyEntity.setUpdatedDate(Instant.now());
            viewVacancyEntitySet.add(viewVacancyEntity);

            return vacancyMapper.toResponse(vacancyRepository.save(vacancyEntity));
        }

        return vacancyMapper.toResponse(vacancyEntity);
    }

    @Override
    @Transactional
    public VacancyResponse updateVacancy(VacancyRequest vacancyRequest, UUID id, UserDetails userDetails) {

        UserAccountEntity userAccountEntity = userAccountService.getUserAccountByEmail(userDetails.getUsername());

        VacancyEntity vacancyEntity = vacancyRepository.findById(id).orElseThrow(VacancyNotFoundException::new);
        if(Objects.isNull(userAccountEntity.getCompany()) ||
                !userAccountEntity.getCompany().equals(vacancyEntity.getCompany())) {
            throw new ChangeVacancyRestrictedPermissionException();
        }
        VacancyEntity vacancyFromMapper = vacancyMapper.toEntity(vacancyRequest);

        vacancyEntity.setActivity(vacancyFromMapper.getActivity());
        vacancyEntity.setTitle(vacancyRequest.getTitle());
        vacancyEntity.setMinSalary(vacancyRequest.getMinSalary());
        vacancyEntity.setMaxSalary(vacancyRequest.getMaxSalary());
        vacancyEntity.setEmployment(vacancyRequest.getEmployment());
        vacancyEntity.setExperience(vacancyEntity.getExperience());
        vacancyEntity.setLocation(vacancyFromMapper.getLocation());

        return vacancyMapper.toResponse(vacancyRepository.save(vacancyEntity));
    }

    @Override
    public VacancyListResponse getVacancyList(Integer page, Integer size, VacancySort vacancySort, Specification<VacancyEntity> specification) {

        if(page < 0) {
            page = DEFAULT_PAGE;
        }

        if (size < 0) {
            size = DEFAULT_SIZE;
        }

        Pageable pageable = PageRequest.of(page, size,
                Sort.by(vacancySort.getSortType(), vacancySort.getValue()));

        Page<VacancyEntity> vacancyEntityPage = vacancyRepository.findAll(specification, pageable);

        return VacancyListResponse.builder()
                .vacancyList(vacancyMapper.toResponseList(vacancyEntityPage.getContent()))
                .totalPages(vacancyEntityPage.getTotalPages())
                .currentPage(vacancyEntityPage.getNumber())
                .build();
    }


}
