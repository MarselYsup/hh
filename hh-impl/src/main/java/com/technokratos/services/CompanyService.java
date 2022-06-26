package com.technokratos.services;

import com.technokratos.dto.request.CompanyInfoRequest;
import com.technokratos.dto.request.FileInfoRequest;
import com.technokratos.dto.response.CompanyInfoResponse;
import com.technokratos.models.CompanyEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface CompanyService {
    CompanyInfoResponse createCompany(CompanyInfoRequest companyInfoRequest, UserDetails userDetails);

    CompanyInfoResponse updateCompany(CompanyInfoRequest companyInfoRequest, UserDetails userDetails);

    CompanyInfoResponse getCompany(UUID id);

    CompanyInfoResponse setAvatar(FileInfoRequest fileInfoRequest, UserDetails userDetails);

    CompanyEntity getCompanyByUserDetails(UserDetails userDetails);
}
