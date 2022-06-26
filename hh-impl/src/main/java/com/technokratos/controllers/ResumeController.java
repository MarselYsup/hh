package com.technokratos.controllers;

import com.technokratos.api.ResumeApi;
import com.technokratos.dto.request.ResumeRequest;
import com.technokratos.dto.response.ResumeListResponse;
import com.technokratos.dto.response.ResumeResponse;
import com.technokratos.enums.ResumeSort;
import com.technokratos.models.ResumeEntity;
import com.technokratos.services.ResumeService;
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

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class ResumeController implements ResumeApi<UserDetails, ResumeEntity> {

    private final ResumeService resumeService;

    @Override
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public ResumeResponse createResume(@Valid ResumeRequest resumeRequest, UserDetails userDetails) {
        return resumeService.createResume(resumeRequest, userDetails);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public ResumeResponse updateResume(@Valid ResumeRequest resumeRequest, UUID id, UserDetails userDetails) {
        return resumeService.updateResume(resumeRequest, id, userDetails);
    }

    @Override
    public ResumeResponse getResume(UUID id, UserDetails userDetails) {
        return resumeService.getResume(id, userDetails);
    }

    @Override
    public void getResumeFile(HttpServletResponse response, UUID id, UserDetails user) {
        resumeService.getFileResume(response, id, user);
    }

    @Override
    public ResumeListResponse getResumeList(Integer page, Integer size, ResumeSort resumeSort,
                                            @Join(path = "employee", alias = "emp")
                                            @Join(path = "education", alias = "ed")
                                            @Join(path = "activity", alias = "ac")
                                            @And({
                                                    @Spec(path = "emp.gender", params = "gender", spec = Equal.class),
                                                    @Spec(path = "emp.city", params = "city", spec = Equal.class),
                                                    @Spec(path = "ed.educationLevel", params = "level",
                                                            spec = Equal.class),
                                                    @Spec(path = "ed.major", params = "major", spec = Equal.class),
                                                    @Spec(path = "title", spec = Like.class),
                                                    @Spec(path = "salary", params = "minSalary",
                                                            spec = GreaterThanOrEqual.class),
                                                    @Spec(path = "salary", params = "maxSalary",
                                                            spec = LessThanOrEqual.class),
                                                    @Spec(path = "ac.activity", params = "activity", spec = Equal.class)
                                            })
                                            Specification<ResumeEntity> resumeSpec) {
        return resumeService.getResumeList(page, size, resumeSort, resumeSpec);
    }

}
