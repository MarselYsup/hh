package com.technokratos.services.impl;

import com.technokratos.dto.request.EducationRequest;
import com.technokratos.dto.request.ResumeRequest;
import com.technokratos.dto.response.ResumeListResponse;
import com.technokratos.dto.response.ResumeResponse;
import com.technokratos.enums.ResumeSort;
import com.technokratos.enums.Role;
import com.technokratos.exceptions.not_found.ResumeNotFoundException;
import com.technokratos.exceptions.restricted_permission.ChangeResumeRestrictedPermissionException;
import com.technokratos.mappers.PortfolioMapper;
import com.technokratos.mappers.ResumeMapper;
import com.technokratos.mappers.custom_converters.ActivityConverter;
import com.technokratos.mappers.custom_converters.SkillConverter;
import com.technokratos.models.*;
import com.technokratos.repositories.ResumeRepository;
import com.technokratos.services.EmployeeService;
import com.technokratos.services.FileService;
import com.technokratos.services.ResumeService;
import com.technokratos.services.UserAccountService;
import com.technokratos.util.DocxGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static com.technokratos.util.consts.PageConst.DEFAULT_PAGE;
import static com.technokratos.util.consts.PageConst.DEFAULT_SIZE;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    private final EmployeeService employeeService;
    private final UserAccountService userAccountService;
    private final FileService fileService;

    private final ResumeMapper resumeMapper;
    private final ActivityConverter activityConverter;
    private final SkillConverter skillConverter;
    private final PortfolioMapper portfolioMapper;

    @Override
    public ResumeResponse createResume(ResumeRequest resumeRequest, UserDetails userDetails) {
        ResumeEntity resumeEntity = resumeMapper.toEntity(resumeRequest);

        EmployeeEntity employeeEntity = employeeService.getEmployeeByUserDetails(userDetails);

        resumeEntity.setEmployee(employeeEntity);

        ResumeEntity finalResumeEntity = resumeRepository.save(resumeEntity);

        finalResumeEntity.setPortfolioSet(
                resumeRequest.getPortfolioSet().stream()
                        .map(portfolioMapper::toEntity)
                        .peek(e -> e.setResume(finalResumeEntity))
                        .collect(Collectors.toSet())
        );

        return resumeMapper.toResponse(resumeRepository.save(finalResumeEntity));
    }

    @Override
    public ResumeResponse updateResume(ResumeRequest resumeRequest, UUID id, UserDetails userDetails) {
        UserAccountEntity userAccountEntity = userAccountService.getUserAccountByEmail(userDetails.getUsername());

        ResumeEntity resumeEntity = resumeRepository.findById(id).orElseThrow(ResumeNotFoundException::new);
        if(Objects.isNull(userAccountEntity.getEmployee()) || !userAccountEntity.getEmployee().equals(resumeEntity.getEmployee())) {
            throw new ChangeResumeRestrictedPermissionException();
        }

        resumeEntity.setTitle(resumeRequest.getTitle());
        resumeEntity.setExperience(resumeEntity.getExperience());
        resumeEntity.setEmployment(resumeRequest.getEmployment());
        resumeEntity.setSalary(resumeRequest.getSalary());
        resumeEntity.setInformation(resumeRequest.getInformation());

        EducationEntity education = resumeEntity.getEducation();
        EducationRequest educationRequest = resumeRequest.getEducation();
        education.setUniversityName(educationRequest.getUniversityName());
        education.setInstituteName(educationRequest.getInstituteName());
        education.setMajor(educationRequest.getMajor());
        education.setStartingDate(educationRequest.getStartingDate());
        education.setCompletionDate(educationRequest.getCompletionDate());

        resumeEntity.setActivity(activityConverter.toEntity(resumeRequest.getActivity()));

        resumeEntity.setSkillSet(
                resumeRequest.getSkillSet().stream().map(skillConverter::toEntity).collect(Collectors.toSet())
        );

        resumeEntity.setPortfolioSet(
                resumeRequest.getPortfolioSet().stream()
                        .map(portfolioMapper::toEntity).peek(e -> e.setResume(resumeEntity)).collect(Collectors.toSet())
        );

        return resumeMapper.toResponse(resumeRepository.save(resumeEntity));
    }

    @Override
    public ResumeResponse getResume(UUID id, UserDetails userDetails) {
        ResumeEntity resumeEntity = resumeRepository.findById(id).orElseThrow(ResumeNotFoundException::new);

        UserAccountEntity userAccountEntity = userAccountService.getUserAccountByEmail(userDetails.getUsername());

        if(userAccountEntity.getRole().equals(Role.COMPANY)) {
            ViewResumeEntity viewResumeEntity = ViewResumeEntity.builder()
                    .resume(resumeEntity)
                    .userAccount(userAccountEntity)
                    .build();

            Set<ViewResumeEntity> viewResumeEntitySet = Optional.ofNullable(resumeEntity.getViewResumeSet())
                    .orElseGet(HashSet::new);

            ViewResumeEntity finalViewResumeEntity = viewResumeEntitySet
                    .stream()
                    .filter(e -> e.equals(viewResumeEntity))
                    .findFirst()
                    .orElse(viewResumeEntity);
            finalViewResumeEntity.setUpdatedDate(Instant.now());
            viewResumeEntitySet.add(finalViewResumeEntity);

            return resumeMapper.toResponse(resumeRepository.save(resumeEntity));
        }

        return resumeMapper.toResponse(resumeEntity);
    }

    @Override
    public void getFileResume(HttpServletResponse response, UUID id, UserDetails user) {
        ResumeEntity resume = resumeRepository.findById(id).orElseThrow(ResumeNotFoundException::new);
        Optional<MongoFileEntity> avatar = fileService.getFileData(resume.getEmployee().getEmployeeAvatar());
        DocxGenerator.writeResumeToResponse(resume, avatar, response);
    }

    @Override
    public ResumeListResponse getResumeList(Integer page, Integer size, ResumeSort resumeSort, Specification<ResumeEntity> resumeSpec) {

        if(page < 0) {
            page = DEFAULT_PAGE;
        }

        if (size < 0) {
            size = DEFAULT_SIZE;
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(resumeSort.getSortType(), resumeSort.getValue()));

        Page<ResumeEntity> resumeEntityPage = resumeRepository.findAll(resumeSpec, pageable);

        return ResumeListResponse.builder()
                .resumeList(resumeMapper.toResponseList(resumeEntityPage.getContent()))
                .totalPages(resumeEntityPage.getTotalPages())
                .currentPage(resumeEntityPage.getNumber())
                .build();
    }
}
