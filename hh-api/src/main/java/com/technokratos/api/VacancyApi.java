package com.technokratos.api;

import com.technokratos.dto.request.VacancyRequest;
import com.technokratos.dto.response.VacancyListResponse;
import com.technokratos.dto.response.VacancyResponse;
import com.technokratos.enums.VacancySort;
import org.springframework.data.jpa.domain.Specification;
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
        value = "Vacancy logic micro-service",
        description = "Create, update and view vacancy"
)
@RequestMapping("/hh/v1/vacancy")
public interface VacancyApi<PRINCIPAL, ENTITY> {

    @ApiOperation(
            value = "Create new vacancy",
            response = VacancyResponse.class,
            code = 201
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request parameters supplied"),
            @ApiResponse(code = 403, message = "Requested user are not authorized")
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    VacancyResponse createVacancy(@RequestBody VacancyRequest vacancyRequest, @AuthenticationPrincipal PRINCIPAL user);

    @ApiOperation(
            value = "Update vacancy",
            response = VacancyResponse.class,
            code = 201
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request parameters supplied"),
            @ApiResponse(code = 403, message = "Requested user are not authorized"),
            @ApiResponse(code = 404, message = "Requested vacancy not found")
    })
    @PutMapping(path = "/{vacancy-id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    VacancyResponse updateVacancy(@RequestBody VacancyRequest vacancyRequest, @PathVariable("vacancy-id") UUID id,
                                  @AuthenticationPrincipal PRINCIPAL user);

    @ApiOperation(
            value = "Get vacancy by id",
            response = VacancyResponse.class
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request parameters supplied"),
            @ApiResponse(code = 403, message = "Requested user are not authorized"),
            @ApiResponse(code = 404, message = "Requested vacancy not found")
    })
    @GetMapping(path = "/{vacancy-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    VacancyResponse getVacancy(@PathVariable("vacancy-id") UUID id, @AuthenticationPrincipal PRINCIPAL user);

    @ApiOperation(
            value = "Get list of vacancies by search properties",
            response = VacancyListResponse.class
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request parameters supplied"),
            @ApiResponse(code = 403, message = "Requested user are not authorized")
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    VacancyListResponse getVacancyList(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                       @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
                                       @RequestParam(name = "sort", required = false,
                                               defaultValue = "TITLE_ASC") VacancySort vacancySort,
                                       Specification<ENTITY> vacancySpec);
}
