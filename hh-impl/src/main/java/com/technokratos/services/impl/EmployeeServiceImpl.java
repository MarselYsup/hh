package com.technokratos.services.impl;

import com.technokratos.dto.request.EmployeeInfoRequest;
import com.technokratos.dto.request.FileInfoRequest;
import com.technokratos.dto.response.EmployeeInfoResponse;
import com.technokratos.exceptions.already_exist.EmployeeAlreadyExistException;
import com.technokratos.exceptions.not_found.CompanyNotFoundException;
import com.technokratos.exceptions.not_found.EmployeeNotFoundException;
import com.technokratos.exceptions.not_found.ResumeNotFoundException;
import com.technokratos.exceptions.not_found.UserAccountNotFoundException;
import com.technokratos.mappers.EmployeeMapper;
import com.technokratos.models.CompanyEntity;
import com.technokratos.models.EmployeeEntity;
import com.technokratos.models.FileInfoEntity;
import com.technokratos.models.UserAccountEntity;
import com.technokratos.repositories.EmployeeRepository;
import com.technokratos.repositories.UserAccountRepository;
import com.technokratos.services.EmployeeService;
import com.technokratos.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final UserAccountRepository userAccountRepository;

    private final EmployeeRepository employeeRepository;

    private final FileService fileService;

    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeInfoResponse createEmployee(EmployeeInfoRequest employeeInfoRequest, UserDetails userDetails) {
        UserAccountEntity userAccount = userAccountRepository
                .findByEmail(userDetails.getUsername())
                .filter(user -> user.getEmployee() == null)
                .orElseThrow(EmployeeAlreadyExistException::new);

        EmployeeEntity employee = employeeMapper.toEntity(employeeInfoRequest);
        userAccount.setEmployee(employee);
        employee.setUserAccount(userAccount);

        return employeeMapper.toResponse(employeeRepository.save(employee));
    }

    @Override
    public EmployeeInfoResponse updateEmployee(EmployeeInfoRequest employeeInfoRequest, UserDetails userDetails) {
        UserAccountEntity userAccount = userAccountRepository
                .findByEmail(userDetails.getUsername())
                .orElseThrow(UserAccountNotFoundException::new);

        if(userAccount.getEmployee() == null) {
            EmployeeEntity employee = employeeMapper.toEntity(employeeInfoRequest);
            userAccount.setEmployee(employee);
            employee.setUserAccount(userAccount);
            return employeeMapper.toResponse(employeeRepository.save(employee));
        }

        EmployeeEntity employee = userAccount.getEmployee();
        employee.setCity(employeeInfoRequest.getCity());
        employee.setDateOfBirth(employeeInfoRequest.getDateOfBirth());
        employee.setGender(employeeInfoRequest.getGender());
        employee.setFirstName(employeeInfoRequest.getFirstName());
        employee.setLastName(employeeInfoRequest.getLastName());
        employee.setPhoneNumber(employeeInfoRequest.getPhoneNumber());

        return employeeMapper.toResponse(employeeRepository.save(employee));
    }

    @Override
    public EmployeeInfoResponse getEmployee(UUID id) {
        return employeeMapper.toResponse(employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new));
    }

    @Override
    public EmployeeInfoResponse setAvatar(FileInfoRequest fileInfoRequestDto, UserDetails userDetails) {
        FileInfoEntity fileInfo = fileService.getFileInfoById(fileInfoRequestDto);
        UserAccountEntity userAccount = userAccountRepository
                .findByEmail(userDetails.getUsername())
                .orElseThrow(UserAccountNotFoundException::new);

        EmployeeEntity employee = employeeRepository.findByUserAccount(userAccount).orElseThrow(EmployeeNotFoundException::new);
        employee.setEmployeeAvatar(fileInfo);

        return employeeMapper.toResponse(employeeRepository.save(employee));
    }

    @Override
    public EmployeeEntity getEmployeeByUserDetails(UserDetails userDetails) {
        UserAccountEntity user = userAccountRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(UserAccountNotFoundException::new);

        if (Objects.isNull(user.getEmployee())) {
            throw new ResumeNotFoundException();
        }
        return user.getEmployee();
    }
}
