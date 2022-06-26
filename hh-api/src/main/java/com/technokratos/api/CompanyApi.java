package com.technokratos.api;

import com.technokratos.dto.request.CompanyInfoRequest;
import com.technokratos.dto.request.FileInfoRequest;
import com.technokratos.dto.response.CompanyInfoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Api(
        value = "Company logic micro-service",
        description = "Create, update, view company and set avatar"
)
@RequestMapping("/hh/v1/company")
public interface CompanyApi<PRINCIPAL> {

    @ApiOperation(
            value = "Create new company",
            response = CompanyInfoResponse.class,
            code = 201
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request parameters supplied"),
            @ApiResponse(code = 403, message = "Requested user are not authorized")
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    CompanyInfoResponse createCompany(@RequestBody CompanyInfoRequest companyInfoRequest,
                                      @AuthenticationPrincipal PRINCIPAL user);

    @ApiOperation(
            value = "Update your company",
            response = CompanyInfoResponse.class
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request parameters supplied"),
            @ApiResponse(code = 403, message = "Requested user are not authorized"),
            @ApiResponse(code = 404, message = "Requested company not found")
    })
    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CompanyInfoResponse updateCompany(@RequestBody CompanyInfoRequest companyInfoRequest,
                                      @AuthenticationPrincipal PRINCIPAL user);

    @ApiOperation(
            value = "Get company by id",
            response = CompanyInfoResponse.class
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request parameters supplied"),
            @ApiResponse(code = 403, message = "Requested user are not authorized"),
            @ApiResponse(code = 404, message = "Requested company not found")
    })
    @GetMapping(value = "/{company-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CompanyInfoResponse getCompany(@PathVariable("company-id") UUID id);

    @ApiOperation(
            value = "Update avatar of your company",
            response = CompanyInfoResponse.class,
            code = 201
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request parameters supplied"),
            @ApiResponse(code = 403, message = "Requested user are not authorized"),
            @ApiResponse(code = 404, message = "Requested avatar not found")
    })
    @PostMapping(value = "/avatar", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    CompanyInfoResponse setAvatar(@RequestBody FileInfoRequest fileInfoRequest,
                                  @AuthenticationPrincipal PRINCIPAL user);
}
