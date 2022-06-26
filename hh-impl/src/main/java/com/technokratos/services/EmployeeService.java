package com.technokratos.services;

import com.technokratos.dto.request.EmployeeInfoRequest;
import com.technokratos.dto.request.FileInfoRequest;
import com.technokratos.dto.response.EmployeeInfoResponse;
import com.technokratos.models.EmployeeEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface EmployeeService {

    EmployeeInfoResponse createEmployee(EmployeeInfoRequest companyInfoRequest, UserDetails user);

    EmployeeInfoResponse updateEmployee(EmployeeInfoRequest companyInfoRequest, UserDetails user);

    EmployeeInfoResponse getEmployee(UUID id);

    EmployeeInfoResponse setAvatar(FileInfoRequest fileInfoRequestDto, UserDetails user);

    EmployeeEntity getEmployeeByUserDetails(UserDetails userDetails);
}
