package com.technokratos.controllers;

import com.technokratos.api.CompanyApi;
import com.technokratos.dto.request.CompanyInfoRequest;
import com.technokratos.dto.request.FileInfoRequest;
import com.technokratos.dto.response.CompanyInfoResponse;
import com.technokratos.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class CompanyController implements CompanyApi<UserDetails> {

    private final CompanyService companyService;

    @Override
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    public CompanyInfoResponse createCompany(@Valid CompanyInfoRequest companyInfoRequest, UserDetails userDetails) {
        return companyService.createCompany(companyInfoRequest, userDetails);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    public CompanyInfoResponse updateCompany(@Valid CompanyInfoRequest companyInfoRequest, UserDetails userDetails) {
        return companyService.updateCompany(companyInfoRequest, userDetails);
    }

    @Override
    public CompanyInfoResponse getCompany(UUID id) {
        return companyService.getCompany(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    public CompanyInfoResponse setAvatar(FileInfoRequest fileInfoRequest, UserDetails userDetails) {
        return companyService.setAvatar(fileInfoRequest, userDetails);
    }


}
