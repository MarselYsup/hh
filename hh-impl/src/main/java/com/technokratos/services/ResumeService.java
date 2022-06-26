package com.technokratos.services;

import com.technokratos.dto.request.ResumeRequest;
import com.technokratos.dto.response.ResumeListResponse;
import com.technokratos.dto.response.ResumeResponse;
import com.technokratos.enums.ResumeSort;
import com.technokratos.models.ResumeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletResponse;

import java.util.UUID;

public interface ResumeService {
    ResumeResponse createResume(ResumeRequest resumeRequest, UserDetails userDetails);

    ResumeResponse updateResume(ResumeRequest resumeRequest, UUID id, UserDetails userDetails);

    ResumeResponse getResume(UUID id, UserDetails userDetails);

    void getFileResume(HttpServletResponse response, UUID id, UserDetails user);

    ResumeListResponse getResumeList(Integer page, Integer size, ResumeSort resumeSort,
                                     Specification<ResumeEntity> resumeSpec);
}
