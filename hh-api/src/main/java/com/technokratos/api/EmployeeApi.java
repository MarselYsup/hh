package com.technokratos.api;

import com.technokratos.dto.request.EmployeeInfoRequest;
import com.technokratos.dto.request.FileInfoRequest;
import com.technokratos.dto.response.EmployeeInfoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(
        value = "Employee logic micro-service",
        description = "Create, update, view employees and update avatar"
)
@RequestMapping("/hh/v1/employee")
public interface EmployeeApi<PRINCIPAL> {

    @ApiOperation(
            value = "Create new employee",
            response = EmployeeInfoResponse.class,
            code = 201
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request parameters supplied"),
            @ApiResponse(code = 403, message = "Requested user are not authorized")
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    EmployeeInfoResponse createEmployee(@RequestBody EmployeeInfoRequest employeeInfoRequest,
                                        @AuthenticationPrincipal PRINCIPAL user);

    @ApiOperation(
            value = "Update employee",
            response = EmployeeInfoResponse.class
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request parameters supplied"),
            @ApiResponse(code = 403, message = "Requested user are not authorized"),
            @ApiResponse(code = 404, message = "Requested employee not found")
    })
    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    EmployeeInfoResponse updateEmployee(@RequestBody EmployeeInfoRequest employeeInfoRequest,
                                        @AuthenticationPrincipal PRINCIPAL user);

    @ApiOperation(
            value = "Get employee by id",
            response = EmployeeInfoResponse.class
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request parameters supplied"),
            @ApiResponse(code = 403, message = "Requested user are not authorized"),
            @ApiResponse(code = 404, message = "Requested employee not found")
    })
    @GetMapping(value = "/{employee-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    EmployeeInfoResponse getEmployee(@PathVariable("employee-id") UUID id);

    @ApiOperation(
            value = "Update your avatar",
            response = EmployeeInfoResponse.class,
            code = 201
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request parameters supplied"),
            @ApiResponse(code = 403, message = "Requested user are not authorized"),
            @ApiResponse(code = 404, message = "Requested avatar not found")
    })
    @PostMapping(value = "/avatar", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    EmployeeInfoResponse setAvatar(@RequestBody FileInfoRequest fileInfoRequestDto,
                                   @AuthenticationPrincipal PRINCIPAL user);
}