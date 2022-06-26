package com.technokratos.controllers;

import com.technokratos.api.EmployeeApi;
import com.technokratos.dto.request.EmployeeInfoRequest;
import com.technokratos.dto.request.FileInfoRequest;
import com.technokratos.dto.response.EmployeeInfoResponse;
import com.technokratos.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class EmployeeController implements EmployeeApi<UserDetails> {

    private final EmployeeService employeeService;

    @Override
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public EmployeeInfoResponse createEmployee(@Valid EmployeeInfoRequest employeeInfoRequest, UserDetails user) {
        return employeeService.createEmployee(employeeInfoRequest, user);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public EmployeeInfoResponse updateEmployee(@Valid EmployeeInfoRequest employeeInfoRequest, UserDetails user) {
        return employeeService.updateEmployee(employeeInfoRequest, user);
    }

    @Override
    public EmployeeInfoResponse getEmployee(UUID id) {
        return employeeService.getEmployee(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public EmployeeInfoResponse setAvatar(FileInfoRequest fileInfoRequestDto, UserDetails user) {
        return employeeService.setAvatar(fileInfoRequestDto, user);
    }

}
