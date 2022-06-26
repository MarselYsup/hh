package com.technokratos.services.impl;

import com.technokratos.dto.request.CompanyInfoRequest;
import com.technokratos.dto.request.FileInfoRequest;
import com.technokratos.dto.response.CompanyInfoResponse;
import com.technokratos.exceptions.already_exist.CompanyAlreadyExistException;
import com.technokratos.exceptions.not_found.CompanyNotFoundException;
import com.technokratos.exceptions.not_found.UserAccountNotFoundException;
import com.technokratos.mappers.CompanyMapper;
import com.technokratos.models.CompanyEntity;
import com.technokratos.models.FileInfoEntity;
import com.technokratos.models.UserAccountEntity;
import com.technokratos.repositories.CompanyRepository;
import com.technokratos.repositories.UserAccountRepository;
import com.technokratos.services.CompanyService;
import com.technokratos.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CompanyServiceImpl implements CompanyService {

    private final UserAccountRepository userAccountRepository;

    private final CompanyRepository companyRepository;

    private final FileService fileService;

    private final CompanyMapper companyMapper;

    @Override
    public CompanyInfoResponse createCompany(CompanyInfoRequest companyInfoRequest, UserDetails userDetails) {
        UserAccountEntity userAccount = userAccountRepository
                .findByEmail(userDetails.getUsername())
                .filter(user -> user.getCompany() == null)
                .orElseThrow(CompanyAlreadyExistException::new);
        CompanyEntity company = companyMapper.toEntity(companyInfoRequest);
        userAccount.setCompany(company);
        company.setUserAccount(userAccount);

        return companyMapper.toResponse(companyRepository.save(company));
    }

    @Override
    public CompanyInfoResponse updateCompany(CompanyInfoRequest companyInfoRequest, UserDetails userDetails) {
        UserAccountEntity userAccount = userAccountRepository
                .findByEmail(userDetails.getUsername())
                .orElseThrow(UserAccountNotFoundException::new);

        if(userAccount.getCompany() == null) {
            CompanyEntity company = companyMapper.toEntity(companyInfoRequest);
            userAccount.setCompany(company);
            company.setUserAccount(userAccount);
            return companyMapper.toResponse(companyRepository.save(company));
        }
        CompanyEntity company = userAccount.getCompany();
        company.setName(companyInfoRequest.getName());
        company.setProfileDescription(companyInfoRequest.getProfileDescription());
        company.setWebsiteUrl(companyInfoRequest.getWebsiteUrl());
        return companyMapper.toResponse(companyRepository.save(company));
    }

    @Override
    public CompanyInfoResponse getCompany(UUID id) {
        return companyMapper.toResponse(companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new));
    }

    @Override
    public CompanyInfoResponse setAvatar(FileInfoRequest fileInfoRequestDto, UserDetails userDetails) {
        FileInfoEntity fileInfo = fileService.getFileInfoById(fileInfoRequestDto);

        UserAccountEntity userAccount = userAccountRepository
                .findByEmail(userDetails.getUsername())
                .orElseThrow(UserAccountNotFoundException::new);

        CompanyEntity company = companyRepository.findByUserAccount(userAccount).orElseThrow(CompanyNotFoundException::new);
        company.setCompanyAvatar(fileInfo);

        return companyMapper.toResponse(companyRepository.save(company));

    }

    @Override
    public CompanyEntity getCompanyByUserDetails(UserDetails userDetails) {
        UserAccountEntity user = userAccountRepository.findByEmail(userDetails.getUsername()).orElseThrow(UserAccountNotFoundException::new);
        if (Objects.isNull(user.getCompany())) {
            throw new CompanyNotFoundException();
        }
        return user.getCompany();
    }
}
